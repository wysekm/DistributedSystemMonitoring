package pl.edu.agh.dsm.catalog.core.model.resource;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SystemResource {
	String name;

	public SystemResource(String name) {
		this.name = name;
	}

	public SystemResource() {
	}

	public String getName() {
		return this.name;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SystemResource))
			return false;
		final SystemResource other = (SystemResource) o;
		if (!other.canEqual((Object) this))
			return false;
		final Object this$name = this.getName();
		final Object other$name = other.getName();
		if (this$name == null ? other$name != null : !this$name
				.equals(other$name))
			return false;
		return true;
	}

	public int hashCode() {
		final int PRIME = 59;
		int result = 1;
		final Object $name = this.getName();
		result = result * PRIME + ($name == null ? 0 : $name.hashCode());
		return result;
	}

	public boolean canEqual(Object other) {
		return other instanceof SystemResource;
	}

	@Override
	public String toString() {
		return "SystemResourceDto [name=" + name + "]";
	}
}
