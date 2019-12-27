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

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ClassifiedService
{
    private final List<Classified> database =
        Collections.synchronizedList(new ArrayList<Classified>());
    private static ClassifiedService service;

    private ClassifiedService()
    {
        loadData();
    }

    public static ClassifiedService getInstance()
    {
        if (service == null)
        {
            service = new ClassifiedService();
        }
        return service;
    }

    public synchronized void save(Classified entity)
    {
        database.add(entity);
    }

    public synchronized List<Classified> findAll()
    {
        return database;
    }

    private void loadData()
    {
        database.add(
            generateClassified(
                "Little Rock, United States", LocalDate.now(), "Blondie. 11 years old, male. Reward.", Arrays.asList("z-image1.jpg"), "-92.2895889", "34.746479"));
       
        database.add(
            generateClassified(
                "Turku, Finland", LocalDate.now(), "Willow. 7 years old, female. 50 libs. Microchipped.", Arrays.asList("z-image2.jpg"), "22.2686901", "60.4514809"));
        
        database.add(
            generateClassified(
                "Lafayette, United States", LocalDate.now(), "Hailey. Male. Reward.", Arrays.asList("z-image3.jpg"), "-92.0198364", "30.2240906"));
        
        database.add(
            generateClassified(
                "Cali, Colombia", LocalDate.now(), "Dingo. 3 years old, male, reward 500$", Arrays.asList("z-image4.jpg"), "-76.514203", "3.367768"));

        database.add(
            generateClassified(
                "Bergen, Norway", LocalDate.now(), "Nala, 2 years old, female. Very friendly dog, Call her by name and she should come to you.", Arrays.asList("z-image5.jpg"), "5.3241501", "60.3929901"));
    }

    private Classified generateClassified(
        String locationName,
        LocalDate lostDate,
        String comments,
        List<String> imagePath,
        String longitude,
        String latitude)
    {
        return new Classified(
            locationName,
            lostDate,
            comments,
            imagePath,
            longitude,
            latitude);
    }
}
