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

package io.odreria.straypet.ui.view;

import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.listbox.ListBox;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import io.odreria.straypet.ui.MainView;
import io.odreria.straypet.ui.model.Classified;
import io.odreria.straypet.ui.model.ClassifiedService;

@Route(value="reel", layout = MainView.class)
@PageTitle("Lost Pets")
@Tag("reel-pet")
public class ReelPet extends VerticalLayout
{
    public ReelPet()
    {
        
        final H2 title = new H2();
        title.setText("Lost Pets");
        title.getStyle().set("background-color", "#FF6978");
        title.getStyle().set("text-align", "center");
        
        ListBox<Classified> lostPets = new ListBox<>();
        lostPets.setItems(ClassifiedService.getInstance().findAll());
        lostPets.setRenderer(new ComponentRenderer<>(pet -> {
            
            Classified lostPet = (Classified) pet;
            Image imgPet =
                new Image("images/" +lostPet.getImagePath().get(0), "");
            imgPet.getStyle().set("width", "198px");
            imgPet.getStyle().set("padding", "5px");
            Label name = new Label(
                lostPet.getLocationName() + ": "  + lostPet.getComments());

            Div panel = new Div(imgPet, name);
            
            panel.getStyle()
                .set("display", "flex")
                .set("alignItems", "center");

            return panel;
        }));
        
        lostPets.addValueChangeListener(event -> {
            Classified lostPet = event.getValue();
            fly(lostPet.getLongitude(), lostPet.getLatitude());
            addMarker(lostPet.getLongitude(), lostPet.getLatitude());
        });
        
        lostPets.setHeight("520px");

        add(title, lostPets);
    }
    
    private void fly(String longitude, String latitude)
    {
        this.getElement().executeJs("fly($0, $1)", longitude, latitude);
    }

    private void addMarker(String longitude, String latitude)
    {
        this.getElement().executeJs("addMarker($0, $1)", longitude, latitude);
    }
}
