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

package io.odreria.map;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.HasSize;
import com.vaadin.flow.component.Tag;
import com.vaadin.flow.component.dependency.JavaScript;
import com.vaadin.flow.component.dependency.StyleSheet;

@SuppressWarnings("serial")
@Tag("odre-map")
@JavaScript("https://api.tiles.mapbox.com/mapbox-gl-js/v1.6.0/mapbox-gl.js")
@JavaScript("https://api.tiles.mapbox.com/mapbox.js/plugins/turf/v2.0.0/turf.min.js")
@StyleSheet("https://api.tiles.mapbox.com/mapbox-gl-js/v1.6.0/mapbox-gl.css")
@JavaScript("frontend://script.js")
public class OdreMap extends Component implements HasSize 
{
    private final String container;
    private final String mapStyle;
    private double zoom;
    private double pitch;
    private double bearing;
    private boolean antialias;
    private Center center;

    public OdreMap(String container, String mapStyle)
    {
        super();
        this.container = container;
        this.mapStyle = mapStyle;
    }

    public double getZoom()
    {
        return zoom;
    }

    protected void setZoom(double zoom)
    {
        this.zoom = zoom;
    }

    public double getPitch()
    {
        return pitch;
    }

    protected void setPitch(double pitch)
    {
        this.pitch = pitch;
    }

    public double getBearing()
    {
        return bearing;
    }

    protected void setBearing(double bearing)
    {
        this.bearing = bearing;
    }

    public boolean isAntialias()
    {
        return antialias;
    }

    protected void setAntialias(boolean antialias)
    {
        this.antialias = antialias;
    }

    public Center getCenter()
    {
        return center;
    }

    protected void setCenter(Center center)
    {
        this.center = center;
    }

    public String getContainer()
    {
        return container;
    }

    public String getMapStyle()
    {
        return mapStyle;
    }

    protected void initialize()
    {
        setId(container);
       
        this.getElement().executeJs(
            "initialize($0, $1, $2, $3, $4, $5)",
            container,
            mapStyle,
            zoom,
            pitch,
            bearing,
            antialias);
    }

    public void addNavigationControl()
    {
        this.getElement().executeJs("addNavigationControl()");
    }
    
    public void fly()
    {
        this.getElement().executeJs("fly()");
    }

    public void showMarkerDraggable()
    {
        this.getElement().executeJs("showMarkerDraggable()");
    }
}
