package ule.ed.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class DoubleLinkedListTest {
	private DoubleLinkedList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new DoubleLinkedList<String>();
	}

	@Test
	public void DoubleLinked_VaciaTest() {
		assertEquals(0,lista.size());
		assertTrue(lista.isEmpty());
		lista.addFirst("A");
		lista.addFirst("B");
		Assert.assertEquals("(B A )", lista.toString());
		assertEquals(2 ,lista.size());
	}
	
	// test addFirst comprueba que dispara excepción
	@Test(expected=NullPointerException.class)
	public void DoubleLinked_AddFirstElementoNuloTest() {
			lista.addFirst(null);
	}
	
	@Test
	public void DoubleLinked_AddFirstTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
	}
	

	//test de iteradores
	@Test
	public void DoubleLinked_IteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iterator();
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertFalse(iter.hasNext());
	}
	
	@Test
	public void DoubleLinked_ReverseIteratorTest() {
		lista.addFirst("2");
		Assert.assertFalse(lista.isEmpty());
		Assert.assertEquals("(2 )", lista.toString());
		lista.addFirst("3");
		Assert.assertEquals("(3 2 )", lista.toString());
		lista.addFirst("7");
		Assert.assertEquals("(7 3 2 )", lista.toString());
		Iterator<String>  iter=lista.iteratorReverse();
		assertTrue(iter.hasNext());
		assertEquals("2", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("3", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("7", iter.next());
		assertFalse(iter.hasNext());
	}
	
	
	//Test iterador excepcion
	@Test (expected = NoSuchElementException.class)
	public void testIteratorReverseException() {
		Iterator<String>  iter=lista.iteratorReverse();
		iter.next();
	}
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void DoubleLinkedNextListaVaciaTest() {
			lista.iterator().next();	}
	
	
	//TEST reverse
	@Test
	public void DoubleLinked_toStringReverseTest() {
	
	lista.addFirst("6");
	Assert.assertFalse(lista.isEmpty());
	Assert.assertEquals("(6 )", lista.toString());
	lista.addFirst("5");
	Assert.assertEquals("(5 6 )", lista.toString());
	lista.addFirst("4");
	Assert.assertEquals("(4 5 6 )", lista.toString());
	lista.addFirst("4");
	Assert.assertEquals("(4 4 5 6 )", lista.toString());
	Assert.assertEquals("(6 5 4 4 )", lista.toStringReverse());
	Assert.assertEquals("(4 4 5 6 )", lista.toString()); // queda en el mismo estado
	
	}
	
	@Test
	public void testCountElem() {
		lista.addFirst("6");
		lista.addFirst("5");
		lista.addFirst("4");
		lista.addFirst("4");
		lista.addFirst("7");
		lista.addFirst("5");
		lista.addFirst("4");
		lista.addFirst("2");
		lista.addFirst("4");
		Assert.assertEquals("(4 2 4 5 7 4 4 5 6 )", lista.toString());
		assertEquals(4, lista.countElem("4"));
		assertEquals(2, lista.countElem("5"));
		assertEquals(1, lista.countElem("7"));
	}

	@Test
	public void testAddLast() {
		lista.addLast("Z");
		assertEquals("(Z )", lista.toString());
		
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.addLast("D");
		assertEquals("(A B C Z D )", lista.toString());
	}
	
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstException() throws EmptyCollectionException {
		lista.removeFirst();
	}
	
	
	@Test
	public void testRemoveFirst() throws EmptyCollectionException {
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E F )", lista.toString());
		assertEquals("A", lista.removeFirst());
		
		assertEquals("(B C D E F )", lista.toString());
		assertEquals("B", lista.removeFirst());
		
		assertEquals("(C D E F )", lista.toString());
		assertEquals("C", lista.removeFirst());
		
		assertEquals("(D E F )", lista.toString());
		assertEquals("D", lista.removeFirst());
		
		assertEquals("(E F )", lista.toString());
		assertEquals("E", lista.removeFirst());
		
		assertEquals("(F )", lista.toString());
		assertEquals("F", lista.removeFirst());
		
		assertEquals("()", lista.toString());
	}
	
	
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastException() throws EmptyCollectionException {
		lista.removelast();
	}
	
	
	@Test
	public void testRemoveLast() throws EmptyCollectionException {
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E F )", lista.toString());
		assertEquals("F", lista.removelast());
		
		assertEquals("(A B C D E )", lista.toString());
		assertEquals("E", lista.removelast());
		
		assertEquals("(A B C D )", lista.toString());
		assertEquals("D", lista.removelast());
		
		assertEquals("(A B C )", lista.toString());
		assertEquals("C", lista.removelast());
		
		assertEquals("(A B )", lista.toString());
		assertEquals("B", lista.removelast());
		
		assertEquals("(A )", lista.toString());
		assertEquals("A", lista.removelast());
		
		assertEquals("()", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElemPosException() {
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E )", lista.toString());
		lista.getElemPos(0);
		lista.getElemPos(-6);
		lista.getElemPos(7);
		
	}

	@Test
	public void testGetElemPos() {
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E F )", lista.toString());
		assertEquals("A", lista.getElemPos(1));
		assertEquals("B", lista.getElemPos(2));
		assertEquals("C", lista.getElemPos(3));
		assertEquals("D", lista.getElemPos(4));
		assertEquals("E", lista.getElemPos(5));
		assertEquals("F", lista.getElemPos(6));
		
		assertEquals("A", lista.getElemPos(-6));
		assertEquals("B", lista.getElemPos(-5));
		assertEquals("C", lista.getElemPos(-4));
		assertEquals("D", lista.getElemPos(-3));
		assertEquals("E", lista.getElemPos(-2));
		assertEquals("F", lista.getElemPos(-1));
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testRemoveElemException() throws EmptyCollectionException {
		lista.addFirst("F");
		lista.addFirst("C");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D C F )", lista.toString());
		lista.removeElem("Z");
	}
	
	@Test
	public void testRemoveElem() throws EmptyCollectionException {
		lista.addFirst("F");
		lista.addFirst("C");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D C F )", lista.toString());
		
		assertEquals(3, lista.removeElem("C"));
		assertEquals("(A B D C F )", lista.toString());
		
		assertEquals(1, lista.removeElem("A"));
		assertEquals("(B D C F )", lista.toString());
		
		assertEquals(4, lista.removeElem("F"));
		assertEquals("(B D C )", lista.toString());
		
		assertEquals(1, lista.removeElem("B"));
		assertEquals("(D C )", lista.toString());
		
		assertEquals(2, lista.removeElem("C"));
		assertEquals("(D )", lista.toString());
		
		assertEquals(1, lista.removeElem("D"));
		assertEquals("()", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddPosException() {
		lista.addPos("A", -2);
	}	

	
	@Test
	public void testAddPos() {
		lista.addFirst("F");
		lista.addFirst("C");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D C F )", lista.toString());
		
		lista.addPos("7", 7);
		assertEquals("(A B C D C F 7 )", lista.toString());
		
		lista.addPos("1", 1);
		assertEquals("(1 A B C D C F 7 )", lista.toString());
		
		lista.addPos("2", 2);
		assertEquals("(1 2 A B C D C F 7 )", lista.toString());
		
		lista.addPos("3", 3);
		assertEquals("(1 2 3 A B C D C F 7 )", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilNotIncludedStringException() {
		lista.addLast("A");
		lista.addLast("B");
		lista.fromUntilIncluded(0, 3);
	
	}
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilNotIncludedStringException2() {
		lista.addLast("A");
		lista.addLast("B");
		lista.fromUntilIncluded(2, -3);
		
	}
	
	@Test
	public void testFromUntilIncludedString() {
		assertEquals("()", lista.fromUntilIncluded(1, 3));
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		assertEquals("(A B C D )", lista.toString());
		
		assertEquals("(A B C )", lista.fromUntilIncluded(1, 3));
		assertEquals("(A B C D )", lista.fromUntilIncluded(1, 4));
		assertEquals("(B C D )", lista.fromUntilIncluded(2, 4));
		assertEquals("(B C D )", lista.fromUntilIncluded(2,5));
		
		assertEquals("(C B A )", lista.fromUntilIncluded(-2, -4));
		assertEquals("(D C B )", lista.fromUntilIncluded(-1, -3));
		assertEquals("()", lista.fromUntilIncluded(-5, -3));
		
		lista.addLast("E");
		lista.addLast("F");
		lista.addLast("G");
		lista.addLast("H");
		assertEquals("(A B C D E F G H )", lista.toString());
		assertEquals("(G F E D C B )", lista.fromUntilIncluded(-2, -7));
		
	}
	
	@Test
	public void testRemovePosLast() throws EmptyCollectionException {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("A");
		lista.addLast("D");
		assertEquals("(A B C D A D )", lista.toString());
		
		assertEquals(5, lista.removePosLast("A"));
		assertEquals("(A B C D D )", lista.toString());
		assertEquals(1, lista.removePosLast("A"));
		assertEquals("(B C D D )", lista.toString());
		assertEquals(4, lista.removePosLast("D"));
		assertEquals("(B C D )", lista.toString());
		assertEquals(2, lista.removePosLast("C"));
		assertEquals("(B D )", lista.toString());
		assertEquals(1, lista.removePosLast("B"));
		assertEquals("(D )", lista.toString());
		assertEquals(1, lista.removePosLast("D"));
		assertEquals("()", lista.toString());
	}
	
	@Test
	public void testIntersec() throws EmptyCollectionException {
		DoubleLinkedList<String> lista2 = new DoubleLinkedList<String>();
		assertEquals("()", lista.intersec(lista2).toString());
		lista2.addLast("A");
		lista2.addLast("A");
		lista2.addLast("Z");
		lista2.addLast("X");
		lista2.addLast("Y");
		lista2.addLast("4");
				
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("A");
		lista.addLast("D");
		assertEquals("(A B C D A D )", lista.toString());
		assertEquals("(A A Z X Y 4 )", lista2.toString());
		assertEquals("(A A )", lista.intersec(lista2).toString());
		
		lista.addLast("C");
		lista.addLast("A");
		lista.addLast("A");
		lista.addLast("Z");
		lista.addLast("Z");
		assertEquals("(A B C D A D C A A Z Z )", lista.toString());
		assertEquals("(A A Z X Y 4 )", lista2.toString());
		assertEquals("(A A Z )", lista.intersec(lista2).toString());
		
		DoubleLinkedList<String> lista3 = new DoubleLinkedList<String>();
		DoubleLinkedList<String> lista4 = new DoubleLinkedList<String>();
		assertEquals("()", lista3.intersec(lista4).toString());
		
		lista3.addLast("Z");
		lista3.addLast("D");
		lista3.addLast("S");
		lista3.addLast("S");
		lista3.addLast("D");
		lista3.addLast("Z");
		
		lista4.addLast("S");
		lista4.addLast("Z");
		lista4.addLast("A");
		lista4.addLast("Z");
		lista4.addLast("D");
		lista4.addLast("Z");
		
		assertEquals("(Z D S S D Z )", lista3.toString());
		assertEquals("(S Z A Z D Z )", lista4.toString());
		assertEquals("(Z Z D S )", lista3.intersec(lista4).toString());		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilIteratorException2() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		lista.addLast("F");
		assertEquals("(A B C D E F )", lista.toString());
		Iterator<String>iter = lista.fromUntilIterator(0, 6);
	}	
	
	@Test(expected = NoSuchElementException.class)
	public void testFromUntilIteratorException3() {
		lista.addLast("A");
		assertEquals("(A )", lista.toString());
		Iterator<String>iter = lista.fromUntilIterator(1, 6);
		assertTrue(iter.hasNext());
		assertEquals("A", iter.next());
		assertFalse(iter.hasNext());
		iter.next();
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilIteratorException4() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		lista.addLast("F");
		assertEquals("(A B C D E F )", lista.toString());
		Iterator<String>iter = lista.fromUntilIterator(-2, 6);
	}
	
	
	@Test
	public void testFromUntilIterator() {
		lista.addLast("A");
		lista.addLast("B");
		lista.addLast("C");
		lista.addLast("D");
		lista.addLast("E");
		lista.addLast("F");
		assertEquals("(A B C D E F )", lista.toString());
		Iterator<String>iter = lista.fromUntilIterator(1, 6);
		Iterator<String>iter2 = lista.fromUntilIterator(2, 5);
		Iterator<String>iter3 = lista.fromUntilIterator(-1, -6);
		Iterator<String>iter4 = lista.fromUntilIterator(-2, -5);
		
		assertTrue(iter.hasNext());
		assertEquals("A", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("B", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("C", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("D", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("E", iter.next());
		assertTrue(iter.hasNext());
		assertEquals("F", iter.next());
		assertFalse(iter.hasNext());
		
		assertEquals("B", iter2.next());
		assertTrue(iter2.hasNext());
		assertEquals("C", iter2.next());
		assertTrue(iter2.hasNext());
		assertEquals("D", iter2.next());
		assertTrue(iter2.hasNext());
		assertEquals("E", iter2.next());
		assertFalse(iter2.hasNext());

		assertTrue(iter3.hasNext());
		assertEquals("F", iter3.next());
		assertTrue(iter3.hasNext());
		assertEquals("E", iter3.next());
		assertTrue(iter3.hasNext());
		assertEquals("D", iter3.next());
		assertTrue(iter3.hasNext());
		assertEquals("C", iter3.next());
		assertTrue(iter3.hasNext());
		assertEquals("B", iter3.next());
		assertTrue(iter3.hasNext());
		assertEquals("A", iter3.next());
		assertFalse(iter3.hasNext());
		
		assertTrue(iter4.hasNext());
		assertEquals("E", iter4.next());
		assertTrue(iter4.hasNext());
		assertEquals("D", iter4.next());
		assertTrue(iter4.hasNext());
		assertEquals("C", iter4.next());
		assertTrue(iter4.hasNext());
		assertEquals("B", iter4.next());
		assertFalse(iter4.hasNext());
	}
}

