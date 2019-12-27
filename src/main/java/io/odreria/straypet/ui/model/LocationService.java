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

package io.odreria.straypet.ui.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LocationService
{
    private final List<Location> database =
        Collections.synchronizedList(new ArrayList<Location>());
    private static LocationService service;

    private LocationService()
    {
        database.add(generateLocation("Houston, Texas, United States", "-95.3632736", "29.7632809"));
        database.add(generateLocation("Omaha, United States", "-95.9404297", "29.7632809"));
        database.add(generateLocation("Tulsa, United States", "-95.9927673", "41.2562599"));
        database.add(generateLocation("Pasadena, United States", "-95.2090988", "29.6910591"));
        database.add(generateLocation("Topeka, United States", "-95.6780396", "39.0483284"));
        database.add(generateLocation("Pearland, United States", "-95.2860489", "29.56357"));
        database.add(generateLocation("San Antonio, United States", "-98.4936295", "29.4241199"));
        database.add(generateLocation("Turku, Finland", "22.2686901", "60.4514809"));
        database.add(generateLocation("Helsinki, Findland ", "24.9354496", "60.1695213"));
        database.add(generateLocation("Espoo, Finland", "24.6522007", "60.2052002"));
        database.add(generateLocation("Vantaa, Finland", "25.0409908", "60.2941399"));
    }
    
    public static LocationService getInstance()
    {
        if (service == null)
        {
            service = new LocationService();
        }
        return service;
    }

    public synchronized List<Location> findAll()
    {
        return database;
    }
    
    private  Location generateLocation(String name, String lng, String lat)
    {
        return new Location(name, lng, lat);
    }
}
