package net.thoughtforge.model;

import java.io.Serializable;

public class PersistentEntity implements Serializable {

	private static final long serialVersionUID = -2182570288852819358L;

    private Integer id;

    private Integer version;

    public Integer getId() {
        return id;
    }

	public Integer getVersion() {
        return version;
    }
}
