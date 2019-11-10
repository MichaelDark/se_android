package ua.temnokhud.lab1.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Note implements Parcelable {

    private long id;
    private long importance;
    private Date createdDateTime = new Date();
    private String imagePath = "";
    private String title;
    private String description;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.id);
        dest.writeLong(this.importance);
        dest.writeLong(this.createdDateTime != null ? this.createdDateTime.getTime() : -1);
        dest.writeString(this.imagePath);
        dest.writeString(this.title);
        dest.writeString(this.description);
    }

    private Note(Parcel in) {
        this.id = in.readLong();
        this.importance = in.readLong();
        long tmpCreatedDateTime = in.readLong();
        this.createdDateTime = tmpCreatedDateTime == -1 ? null : new Date(tmpCreatedDateTime);
        this.imagePath = in.readString();
        this.title = in.readString();
        this.description = in.readString();
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel source) {
            return new Note(source);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

}

