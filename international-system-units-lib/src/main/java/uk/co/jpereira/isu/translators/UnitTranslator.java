package uk.co.jpereira.isu.translators;

import uk.co.jpereira.utils.SharedMemoryObject;

public abstract class UnitTranslator<Unit1, Unit2> extends SharedMemoryObject{
	private final Class<Unit1> from;
	private final Class<Unit2> to;

	/**
	 * Translator constructor
	 * @param from Starting unit
	 * @param to Destination unit
	 */
	public UnitTranslator (Class<Unit1> from, Class<Unit2> to) {
		super();
		this.from = from;
		this.to = to;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	 public int hashCode(){
		 return from.hashCode() + to.hashCode();
	 }

	/**
	 * Retrieve class from Starting Unit
	 * @return Class of starting unit
	 */
	public final Class<Unit1> from() {
		return from;
	}
	/**
	 * Retrieve class from Destination Unit
	 * @return Class of Destination unit
	 */
	public final Class<Unit2> to() {
		return to;
	}
	
    /* (non-Javadoc)
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final UnitTranslator<?, ?> other = (UnitTranslator<?, ?>) obj;
        if(other.from == from && other.to == to)
        	return true;
        return false;
    }

    /**
     * Match current translator with the 2 given classes
     * @param from Class of starting unit
     * @param to Class of destination unit
     * @return True in case they match
     */
    public boolean match (Class<?> from, Class<?> to) {
        return from().equals(from) && to().equals(to);
    }
    
}
