package pl.edu.agh.dsm.monitor.core.model.user;

import java.util.UUID;

public class ApplicationUser {

	String name;

	public ApplicationUser(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		ApplicationUser that = (ApplicationUser) o;

		if (name != null ? !name.equals(that.name) : that.name != null) return false;

		return true;
	}

	@Override
	public int hashCode() {
		return name != null ? name.hashCode() : 0;
	}
}
