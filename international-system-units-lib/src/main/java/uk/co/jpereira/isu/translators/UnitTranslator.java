package uk.co.jpereira.isu.translators;

import uk.co.jpereira.utils.SharedMemoryObject;



public abstract class UnitTranslator<Unit1, Unit2> extends SharedMemoryObject{
	private final Class<Unit1> from;
	private final Class<Unit2> to;
	public UnitTranslator (Class<Unit1> from, Class<Unit2> to) {
		super();
		this.from = from;
		this.to = to;
	}
	@Override
	 public int hashCode(){
		 return from.hashCode() + to.hashCode();
	 }

	public final Class<Unit1> from() {
		return from;
	}
	public final Class<Unit2> to() {
		return to;
	}
	
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

    public boolean match (Class<?> from, Class<?> to) {
        return from().equals(from) && to().equals(to);
    }
    
}
