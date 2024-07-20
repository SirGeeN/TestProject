package com.misys.portal.common.bulk.field;

import com.misys.portal.common.bulk.BulkObject;
import com.misys.portal.common.currency.CurrencyData;
import com.misys.portal.common.currency.UnknownCurrencyException;
import com.misys.portal.common.tracer.InvalidDataException;

public class CurrencyField
        extends StringField {

    private static final int maxLength = 3;

    public CurrencyField() {
        super(3);
    }

    public CurrencyField(String curCode) {
        setValue(curCode);
    }

    public BulkField cloneBulk() {
        CurrencyField clone = new CurrencyField();
        clone.setName(getName());
        clone.setValue(getValue());
        clone.setTransactionOnly(isTransactionOnly());
        clone.setTemp(isTemp());
        return clone;
    }

    public int getMaxLength() {
        return 3;
    }

    public final void setValue(String newValue) {
        try {
            if ((newValue != null) && (newValue.length() != 0) && (!CurrencyData.containsIsoCode(newValue))) {
                BulkObject owner = getOwner();
                String ownerName = owner != null ? owner.getClass().getName() : "unknown object";
                String[] args = {newValue, getName(), ownerName};
                throw new InvalidDataException("Currency field, value: "
                        + newValue
                        + " is not allowed for "
                        + getName()
                        + ", object: "
                        + ownerName, "BFD_ERROR_02", args);
            }
        } catch (UnknownCurrencyException localUnknownCurrencyException) {
            String[] args
                    = {newValue, getName()};
            throw new InvalidDataException("Currency field, value: "
                    + newValue
                    + " is not allowed for "
                    + getName(), "BFD_ERROR_02", args);
        }
        super.setValue(newValue);
    }

    public final void setValue2(String newValue) {
        try {
            if ((newValue != null) && (newValue.length() != 0) && (!CurrencyData.containsIsoCode(newValue))) {
                BulkObject owner = getOwner();
                String ownerName = owner != null ? owner.getClass().getName() : "unknown object";
                String[] args
                        = {newValue, getName(), ownerName};
                throw new InvalidDataException("Currency field, value: "
                        + newValue
                        + " is not allowed for "
                        + getName()
                        + ", object: "
                        + ownerName, "BFD_ERROR_02", args);
            }
        } catch (UnknownCurrencyException uce) {
            throw new InvalidDataException("Currency field, value: "
                    + newValue
                    + " is not allowed for "
                    + getName(), uce);
        }
        super.setValue(newValue);
    }

    public void setValue(String newValue, String language) {
        setValue(newValue);
    }

    public void setValue(String newValue, String customAttribute, String language) {
        setValue(newValue);
    }
}
