package com.zainco.library.databinding.vogellabaseadapter;


import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/*
* Any plain old Java object (POJO) can be used for data binding. But if updates in the data model should also update the user interface
* , the objects must be able to notify about data changes. There are three different data change notification mechanisms:
*  * observable objects * observable fields * observable collections

Android provides the BaseObservable class which you can extend. The data class is responsible for notifying when the properties change.
*  This is done by assigning a @Bindable annotation to the getter and notifying in the setter.*/
public class TemperatureData extends BaseObservable/* the objects must be able to notify about data changes.*/ {
    private String location;
    private String celsius;
    public String url = "http://lorempixel.com/400/200/";

    public TemperatureData(String location, String celsius) {
        this.location = location;
        this.celsius = celsius;
    }

    @Bindable/*The data class is responsible for notifying when the properties change. This is done by assigning a @Bindable annotation to the getter*/
    public String getCelsius() {
        return celsius;
    }

    @Bindable
    public String getLocation() {
        return location;
    }

    public void setCelsius(String celsius) {
        this.celsius = celsius;
        /*The data class is responsible for notifying when the properties change. This is done by notifying notifyPropertyChanged  in setter to Notify any listeners
         * This listener is invoked on every update and it updates the corresponding views. This ensures that updates in the model updates also the UI.
         */
        /*
         * The BR class is not yet generated. After the definition of the layout file, the is will be generated by the Gradle tooling.*/
        notifyPropertyChanged(BR.celsius/*generated class*/);
        //If the BR class is missing, select Build  Clean followed by Build  Make Project.
    }

    public void setLocation(String location) {
        this.location = location;
        notifyPropertyChanged(BR.location);
    }
    /*
    * Alternatively to create a observable class, you can also use ObservableField and its subclass for properties.

private class TemperatureData {
   public final ObservableField<String> celsius = new ObservableField<>();
   public final ObservableField<String> location =  new ObservableField<>();
   *To access such fields in your code, use the set and get methods.

temp.location.set("Hamburg");
String celsius  = temp.celsius.get();
}*/

}
