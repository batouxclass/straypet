package io.odreria.straypet.ui.view;
/*
 * Copyright 2019 Diego Cortes
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.vaadin.flow.component.ClickEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.Notification.Position;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.upload.MultiFileReceiver;
import com.vaadin.flow.component.upload.Upload;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import io.odreria.straypet.ui.MainView;
import io.odreria.straypet.ui.model.Classified;
import io.odreria.straypet.ui.model.ClassifiedService;
import io.odreria.straypet.ui.model.Location;
import io.odreria.straypet.ui.model.LocationService;

@Route(value="classified", layout = MainView.class)
@PageTitle("Report Lost Pet")
@Tag("classified-pet")
public class ClassifiedPet extends FormLayout
{
    public static final String LOST_PETS_IMAGES = "src/main/webapp/images";

    private final H2 title = new H2();
    private final DatePicker lostDate = new DatePicker();
    private final TextArea comments = new TextArea();
    private Upload upload;

    private final ComboBox<Location> locations = new ComboBox<>();
    private Location locationPet;

    private final Button saveBtn = new Button("Save");

    Classified report = new Classified();
    
    private List<String> imagePath;

    final Binder<Classified> binder = new Binder<>(Classified.class);

    public ClassifiedPet()
    {  
        setResponsiveSteps(
            new FormLayout.ResponsiveStep("25em", 1));

        title.setText("Report Lost Pet");
        title.getStyle().set("background-color", "#FF6978");
        title.getStyle().set("text-align", "center");

        createDatePicker();
        comments.setPlaceholder("Rufus, 2 yeard old, male.");

        File folderImages = new File(LOST_PETS_IMAGES);
        if (!folderImages.exists())
        {
            folderImages.mkdir();
        }

        imagePath = new ArrayList<>();

        upload = new Upload((MultiFileReceiver) (name, mimeType) -> {
            File file = new File(new File(LOST_PETS_IMAGES), name);
            try {
                imagePath.add(name);
                return new FileOutputStream(file);
            }
            catch (FileNotFoundException fnex)
            {
                fnex.printStackTrace();
                return null;
            }
        });
 
        upload.setAcceptedFileTypes("image/jpeg", "image/png");
        upload.setDropLabel(new Label("Upload pet images"));
        upload.setMaxFiles(5);

        add(title, upload);
        addFormItem(lostDate, "Lost Date");

        locations.setItems(LocationService.getInstance().findAll());
        locations.setClearButtonVisible(true);
        
        setEvents();
        createValidations();

        addFormItem(comments, "Additional information");
        addFormItem(locations, "Where did you last see your pet ?");
        add(saveBtn);
    }

    private void createValidations()
    {
        binder.setBean(report);

        binder.forField(lostDate)
            .asRequired()
            .bind(Classified::getLostDate, Classified::setLostDate);
    }

    private void setEvents()
    {
        upload.addSucceededListener(event -> {
            Notification.show("Upload completed!", 3000, Position.TOP_CENTER);
        });

        saveBtn.addClickListener(saveClick());

        locations.addValueChangeListener(event -> {
            locationPet = event.getValue();
        });
    }

    private void createDatePicker()
    {
        lostDate.setClearButtonVisible(true);
        lostDate.setPlaceholder("Lost date");
        lostDate.setRequired(true);
        lostDate.setMax(LocalDate.now());
        lostDate.setValue(LocalDate.now());
        lostDate.setRequired(true);
    }


    private ComponentEventListener<ClickEvent<Button>> saveClick()
    {
        return event -> {

            report.setImagePath(imagePath);

            if (binder.validate().isOk() && imagePath.size() > 0 && locationPet != null)
            {
                report.setLocationName(locationPet.getName());
                report.setLongitude(locationPet.getLongitude());
                report.setLatitude(locationPet.getLatitude());
                report.setComments(comments.getValue());
                
                ClassifiedService.getInstance().save(binder.getBean());
                UI.getCurrent().navigate(ReelPet.class);
                Notification.show("Report saved", 3000, Position.TOP_CENTER);
            }
            else
            {
                Notification.show("Fields are required!", 3000, Position.TOP_CENTER);
            }
        };
    }
}
