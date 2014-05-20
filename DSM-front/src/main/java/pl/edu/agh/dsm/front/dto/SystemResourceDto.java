package pl.edu.agh.dsm.front.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class SystemResourceDto {
	String name;

	public SystemResourceDto(String name) {
		this.name = name;
	}

	public SystemResourceDto() {
	}

	public String getName() {
		return this.name;
	}

	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof SystemResourceDto))
			return false;
		final SystemResourceDto other = (SystemResourceDto) o;
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
		return other instanceof SystemResourceDto;
	}

	@Override
	public String toString() {
		return "SystemResourceDto [name=" + name + "]";
	}
}
