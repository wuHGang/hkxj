package cn.hkxj.platform.pojo;

import com.google.common.base.MoreObjects;
import com.google.common.base.Objects;

public class Room {
    private Integer id;

    private Building area;

    private Direction direction;

    private Integer floor;

    private Integer number;

    private String name;

    private Byte isAllow;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Building getArea() {
        return area;
    }

    public void setArea(Building area) {
        this.area = area;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public Integer getFloor() {
        return floor;
    }

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Byte getIsAllow() {
        return isAllow;
    }

    public void setIsAllow(Byte isAllow) {
        this.isAllow = isAllow;
    }

	@Override
	public String toString() {
		return MoreObjects.toStringHelper(this)
				.add("id", id)
				.add("area", area)
				.add("direction", direction)
				.add("floor", floor)
				.add("number", number)
				.add("name", name)
				.add("isAllow", isAllow)
				.toString();
	}
}