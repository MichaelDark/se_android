package ua.temnokhud.lab1.model;


import androidx.annotation.DrawableRes;
import androidx.collection.LongSparseArray;

import lombok.Getter;
import ua.temnokhud.lab1.R;

public enum Importance {

    MINOR(1, R.drawable.ic_number_1),
    MAJOR(2, R.drawable.ic_number_2),
    CRITICAL(3, R.drawable.ic_number_3);

    private static LongSparseArray<Importance> valuesLongSparseArray;

    static {
        valuesLongSparseArray = new LongSparseArray<>();
        for (Importance importance : values()) {
            valuesLongSparseArray.append(importance.id, importance);
        }
    }

    @Getter
    private long id;

    @Getter
    @DrawableRes
    private int iconDrawableRedId;

    Importance(long id, int iconDrawableRedId) {
        this.id = id;
        this.iconDrawableRedId = iconDrawableRedId;
    }

    public static Importance getById(long id) {
        return valuesLongSparseArray.get(id, MINOR);
    }

}
