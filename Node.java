/*
 * Sequential B*-Tree implementation for the 
 * Concurrent Search Tree Project for
 * Parallel Computing I
 *
 * Author: David C. Larsen <dcl9934@cs.rit.edu>
 * Date: April. 12, 2011
 */

import java.lang.reflect.Array;

/** 
 * A B-Tree node.
 */
class Node<K extends Comparable,V>
{
	private final int numKeysPerNode = 7;

	private int numKeys;
	private K[] keys;
	private Node<K,V>[] children;
	private Node<K,V> parent = null;

	@SuppressWarnings({"unchecked"})
	public Node( K key,
				 V value,
				 Node<K,V> parent )
	{
		// Like: keys = new K[numKeysPerNode], but working around Java's
		// type-erasure approach to generics.
		// This cast will always work because Array dynamically creates the
		// generic array of the correct type. Still, we have to do an
		// "unchecked" cast, and we don't want to be warned about it,
		// because we've already guaranteed the type safety.
		keys = (K[]) Array.newInstance( key.getClass(), numKeysPerNode );
		children = new Node[numKeysPerNode+1];
        numKeys = 0;
		this.parent = parent;
	}

	/**
	 * Find the lowest number in the range of Keys in this Node.
     *
     * @return The lowerbound of the values in this node.
	 */
	public K lowerBound()
	{
		return keys[0];
	}

	/**
	 * Find the highest number in the range of Keys in this Node.
     *
     * @return The upperbound of the values in this node.
	 */
	public K upperUpper()
	{
		return keys[numKeys-1];
	}
	
	/**
	 * Returns a child node such that K is within its bounds.
	 */
	public Node<K,V> getChild( K key )
	{
		// Linear search, get the K'th child
		int sentry = 0;
//		while( keys[sentry] < key && sentry < keys.length)
		while( keys[sentry].compareTo(key) < 0 && sentry < keys.length)
		{
			sentry++;
		}
		return children[sentry];
	}
}
