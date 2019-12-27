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

public class OdreMapBuilder
{
    private double zoom = 15.5;
    private double pitch = 60;
    private double bearing = -60;
    private boolean antialias = true;
    private Center center;

    private final OdreMap odreMap;

    public OdreMapBuilder(String container, String style)
    {
        odreMap = new OdreMap(container, style);
    }

    public OdreMapBuilder withZoom(double zoom)
    {
        this.zoom = zoom;
        return this;
    }

    public OdreMapBuilder withPitch(double pitch)
    {
        this.pitch = pitch;
        return this;
    }

    public OdreMapBuilder withBearing(double bearing)
    {
        this.bearing = bearing;
        return this;
    }

    public OdreMapBuilder withAntialias(boolean antialias)
    {
        this.antialias = antialias;
        return this;
    }

    public OdreMapBuilder withCenter(Center center)
    {
        this.center = center;
        return this;
    }

    public OdreMap build()
    {
        odreMap.setZoom(zoom);
        odreMap.setPitch(pitch);
        odreMap.setBearing(bearing);
        odreMap.setAntialias(antialias);
        odreMap.setCenter(center);
        odreMap.initialize();
        return odreMap;
    }

}
