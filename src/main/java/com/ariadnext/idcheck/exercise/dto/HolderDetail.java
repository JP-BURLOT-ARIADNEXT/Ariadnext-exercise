package com.ariadnext.idcheck.exercise.dto;

import java.util.ArrayList;
import java.util.List;

public class HolderDetail {
	private List<String> lastName = new ArrayList<>();

	private List<String> firstName = new ArrayList<>();

	private String nationality;

	private EventDate birthDate;

	public List<String> getLastName() {
		return lastName;
	}

	public void setLastName(List<String> lastName) {
		this.lastName = lastName;
	}

	public List<String> getFirstName() {
		return firstName;
	}

	public void setFirstName(List<String> firstName) {
		this.firstName = firstName;
	}

	public String getNationality() {
		return nationality;
	}

	public void setNationality(String nationality) {
		this.nationality = nationality;
	}

	public EventDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(EventDate birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "HolderDetail [lastName=" + lastName + ", firstName=" + firstName + ", nationality=" + nationality
				+ ", birthDate=" + birthDate + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((birthDate == null) ? 0 : birthDate.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HolderDetail other = (HolderDetail) obj;
		if (birthDate == null) {
			if (other.birthDate != null)
				return false;
		} else if (!birthDate.equals(other.birthDate))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (nationality == null) {
			if (other.nationality != null)
				return false;
		} else if (!nationality.equals(other.nationality))
			return false;
		return true;
	}

}
