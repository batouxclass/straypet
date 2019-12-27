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

package io.odreria.straypet.ui;

import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.page.Viewport;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.router.RouterLayout;
import com.vaadin.flow.router.RouterLink;
import com.vaadin.flow.server.InitialPageSettings;
import com.vaadin.flow.server.PWA;
import com.vaadin.flow.server.PageConfigurator;

import io.odreria.map.OdreMap;
import io.odreria.map.OdreMapBuilder;
import io.odreria.straypet.ui.view.ClassifiedPet;
import io.odreria.straypet.ui.view.ReelPet;

/**
 *  Main screen of the application. It implements RouterLayout interface for a navigation between components.
 *
 *  @author Diego Cortes
 */
@PWA(name = "Stray Pet", shortName = "StrayPet")
@Viewport("width=device-width, minimum-scale=1.0, initial-scale=1.0, user-scalable=yes")
@Route("")
public class MainView extends HorizontalLayout implements RouterLayout, PageConfigurator
{
    private final OdreMap map;

    private final VerticalLayout verticalLayout;
    private final Div navigation;

    public MainView()
    {
        RouterLink reelPet = new RouterLink(null, ReelPet.class);
        Icon listIcon = new Icon(VaadinIcon.LINES_LIST);
        listIcon.setSize("50px");
        reelPet.add(listIcon, new Text("Lost Pets"));
        reelPet.addClassName("main-view_nav_element");
        reelPet.getStyle().set("display", "block");
        reelPet.getStyle().set("padding", "12px");

        RouterLink classifiedPet = new RouterLink(null, ClassifiedPet.class);
        Icon newIcon = new Icon(VaadinIcon.DATE_INPUT);
        newIcon.setSize("50px");
        classifiedPet.add(newIcon, new Text("Report lost pet"));
        reelPet.addClassName("main-view_nav_element");
        
        Image logo = new Image("images/spet.png", "");
        logo.getStyle().set("width", "82px");
        logo.getStyle().set("padding", "5px");
        
        setPadding(false);
        setSpacing(false);
        setSizeFull();
        setDefaultVerticalComponentAlignment(Alignment.STRETCH);

        OdreMapBuilder odreMapBuilder =
                new OdreMapBuilder(
                    "map",
                    "mapbox://styles/arkantosdfc1/ck3uihcfi0p1q1cqmpo61tl1g");
     
        map = odreMapBuilder.build();
        map.addNavigationControl();
        map.setHeightFull();
        map.setWidthFull();

        navigation = new Div();
        navigation.getStyle().set("flexShrink", "0");
        navigation.setClassName("main-view_navigation");
        navigation.setHeightFull();
        navigation.setWidth("87px");
        navigation.getStyle().set("background-color", "#FF6978");
        navigation.getStyle().set("text-align", "center");
       
        navigation.add(logo, reelPet, classifiedPet);
        

        verticalLayout = new VerticalLayout();
        verticalLayout.setHeightFull();
        verticalLayout.setPadding(false);
        verticalLayout.setSpacing(false);
        verticalLayout.setWidth("800px");
        verticalLayout.setClassName("main-view_navigation_control");
        verticalLayout.getStyle()
            .set("flexShrink", "0")
            .set("background-color", "#FFFCF9")
            .set("text-align", "center");


        addClassName("main-view");

        add(navigation);
        setupContentLayout();
        add(verticalLayout);
    }

    private void setupContentLayout()
    { 
        final Div content = new Div();
        content.setHeightFull();
        content.setWidthFull();
        content.getStyle().set("display", "flex");
        content.setClassName("main-view_content");
        content.getStyle().set("alignContent", "start");
        content.add(map);
        
        verticalLayout.add(content);
    }

    @Override
    public void configurePage(InitialPageSettings settings)
    {
        settings.addMetaTag("apple-mobile-web-app-capable", "yes");
        settings.addMetaTag("apple-mobile-web-app-status-bar-style", "black");
    }

}