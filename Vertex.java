
import java.util.*;
/**
 * Representation of a graph vertex
 */
public class Vertex {
	// label attached to this vertex
	private String label;
	
	// identify Vertex predecessor, distance and marked
	private Vertex predecessor;
	private Collection<Vertex> descendants;
//	private Vertex successor;
	private int distance;
	private boolean mark;

	public Vertex(String label) {
		/**
		 * Construct a new vertex
		 * @param label
		 *            the label attached to this vertex
		 */
		if (label == null)
			throw new IllegalArgumentException("null");
		this.label = label;
		descendants = new ArrayList(); 
	}
	
	public void setPredecessor (Vertex p) {
		this.predecessor = p;
	}
	public void addDescendant (Vertex a) {
		this.descendants.add(a);
		
		// ensure that all predecessors of vertex also receive a as a descendant
		Vertex p = a.getPredecessor();
		while (p != null) {
			//ensure we do not add a copy
			Iterator allDescendants = p.getDescendants().iterator();
			boolean duplicate = false;
			while (allDescendants.hasNext()) {
				if (allDescendants.next().toString().equals(a.toString())) {
					duplicate = true;
					break;
				}
			} // if there is no copy then add
			if (!duplicate) { p.descendants.add(a); }
 			// go to next predecessor
			p = p.getPredecessor();
		}
	}
	public void setDistance (int d) {
		this.distance = d;
	}
	public void marked (boolean m) {
		this.mark = m;
	}
	public Vertex getPredecessor () {
		return this.predecessor;
	}
	public int getDistance () {
		return this.distance;
	}
	public Collection<Vertex> getDescendants () {
		return this.descendants;
	}
	public boolean isMarked () {
		return this.mark;
	}


	public String getLabel() {
		/**
		 * Get a vertex label
		 * @return the label attached to this vertex
		 */
		return "v("+label+")";
	}


	public String toString() {
		/**
		 * A string representation of this object
		 * @return the label attached to this vertex
		 */
		return label;
	}

	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((label == null) ? 0 : label.hashCode());
		return result;
	}

	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final Vertex other = (Vertex) obj;
		if (label == null) {
			return other.label == null;
		} else {
			return label.equals(other.label);
		}
	}

}

