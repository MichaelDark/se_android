package ua.temnokhud.practice2.model;

import androidx.annotation.NonNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InputColor {

    private double red;
    private double green;
    private double blue;

    public InputColor withRed(double red) {
        this.red = red;
        return this;
    }

    public InputColor withGreen(double green) {
        this.green = green;
        return this;
    }

    public InputColor withBlue(double blue) {
        this.blue = blue;
        return this;
    }

    public int getIntColor() {
        int redComponent = ((int) red << 16) & 0x00FF0000;
        int greenComponent = ((int) green << 8) & 0x0000FF00;
        int blueComponent = (int) blue & 0x000000FF;

        return 0xFF000000 | redComponent | greenComponent | blueComponent;
    }

    @NonNull
    @Override
    public String toString() {
        return "InputColor{" +
                "red=" + red +
                ", green=" + green +
                ", blue=" + blue +
                ", color=" + Integer.toHexString(getIntColor()) +
                '}';
    }
}
