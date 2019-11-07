package struct.contract;

public interface BinaryHeapContract<K> extends HeapContract<K> {
	@Override BinaryHeapContract<K> createEmpty();
	@Override BinaryHeapContract<K> copy();

	@Override 
	default int dimension() {
		return 2;
	}
	
}
