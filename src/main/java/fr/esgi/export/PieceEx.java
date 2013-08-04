package fr.esgi.export;

import java.util.ArrayList;
import java.util.List;

public class PieceEx {

	// =========================================================================
	// ATTRIBUTES
	// =========================================================================
	
	private List<String> location = new ArrayList<String>();

	private String color;
	
	private String name;

	// =========================================================================
	// CONSTRUCTORS
	// =========================================================================

	public PieceEx() {
		super();
	}
	
	// =========================================================================
	// METHODS
	// =========================================================================

	
	// =========================================================================
	// OVERRIDES
	// =========================================================================

	// =========================================================================
	// GETTERS & SETTERS
	// =========================================================================

	public List<String> getLocation() {
		return location;
	}

	public void setLocation(List<String> location) {
		this.location = location;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
