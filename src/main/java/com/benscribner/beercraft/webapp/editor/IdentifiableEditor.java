package com.benscribner.beercraft.webapp.editor;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.propertyeditors.PropertiesEditor;

import com.benscribner.beercraft.model.Identifiable;
import com.benscribner.beercraft.service.IdentifiableManager;

/**
 * Editor to convert IDs to objects
 */
public class IdentifiableEditor<T extends Identifiable> extends PropertiesEditor {

    private final IdentifiableManager<T> manager;

    /**
     * Constructor which sets the manager
     * 
     * @param manager the manager to set
     */
    public IdentifiableEditor(IdentifiableManager<T> manager) {
        this.manager = manager;
    }

    /**
     * @see org.springframework.beans.propertyeditors.PropertiesEditor#setAsText(java.lang.String)
     */
    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if (StringUtils.isBlank(text) || "0".equals(text)) {
            return;
        }
        
        T object = manager.get(text);
        setValue(object);
    }
}