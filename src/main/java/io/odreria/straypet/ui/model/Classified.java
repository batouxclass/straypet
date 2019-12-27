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
import java.util.List;

public class Classified
{
    private String locationName;
    private LocalDate lostDate;
    private String comments;
    private List<String> imagePath;
    private String longitude;
    private String latitude;

    public Classified()
    {
    }

    public Classified(
        String locationName,
        LocalDate lostDate,
        String comments,
        List<String> imagePath,
        String longitude,
        String latitude)
    {
        super();
        this.locationName = locationName;
        this.lostDate = lostDate;
        this.comments = comments;
        this.imagePath = imagePath;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public LocalDate getLostDate()
    {
        return lostDate;
    }
    public String getLocationName()
    {
        return locationName;
    }
    public void setLocationName(String locationName)
    {
        this.locationName = locationName;
    }
    public void setLostDate(LocalDate lostDate)
    {
        this.lostDate = lostDate;
    }
    public String getComments()
    {
        return comments;
    }
    public void setComments(String comments)
    {
        this.comments = comments;
    }
    public List<String> getImagePath()
    {
        return imagePath;
    }
    public void setImagePath(List<String> imagePath)
    {
        this.imagePath = imagePath;
    }
    public String getLongitude()
    {
        return longitude;
    }
    public void setLongitude(String longitude)
    {
        this.longitude = longitude;
    }
    public String getLatitude()
    {
        return latitude;
    }
    public void setLatitude(String latitude)
    {
        this.latitude = latitude;
    }
}
