package uk.co.jpereira.views.utils;

public class ComboBoxItem<ObjectToSave>
{
    private String key;
    private ObjectToSave value;

    public ComboBoxItem(String key, ObjectToSave unit)
    {
        this.key = key;
        this.value = unit;
    }

    @Override
    public String toString()
    {
        return key;
    }

    public String getKey()
    {
        return key;
    }

    public ObjectToSave getValue()
    {
        return value;
    }
}