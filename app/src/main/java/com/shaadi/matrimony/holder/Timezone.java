package com.shaadi.matrimony.holder;
import javax.annotation.Generated;

//@Entity(tableName = "zone_table")
@Generated("jsonschema2pojo")
public class Timezone {
/*    @PrimaryKey(autoGenerate = true)
    private int _tId;*/
    private String offset;
    private String description;

    public String getOffset() {
        return offset;
    }

    public void setOffset(String offset) {
        this.offset = offset;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

/*    public int get_tId() {
        return _tId;
    }

    public void set_tId(int _tId) {
        this._tId = _tId;
    }*/
}
