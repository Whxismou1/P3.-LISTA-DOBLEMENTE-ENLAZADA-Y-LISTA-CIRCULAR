package ule.ed.list;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Iterator;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class CircularDoubleLinkedListTest {
	private CircularDoubleWithHeaderList<String> lista;
	
	@Before
	public void setUp() {
		 lista= new CircularDoubleWithHeaderList<String>();
	}

	@Test
	public void DoubleLinked_VaciaTest() {
		assertEquals(0,lista.size());
		assertTrue(lista.isEmpty());
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
	
	
	
	
	// TEST ITERADORES EN LISTA VACÍA
	@Test(expected=NoSuchElementException.class)
	public void DoubleLinkedNextListaVaciaTest() {
			lista.iterator().next();
	}
	@Test(expected=NoSuchElementException.class)
	public void DoubleLinkedNextListaVaciaTestReverse() {
		lista.iteratorReverse().next();
	}
	
	
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
	public void testAddLast() {
		lista.addLast("Z");
		Assert.assertEquals("(Z )", lista.toString());
		lista.addLast("A");
		Assert.assertEquals("(Z A )", lista.toString());
	}
	@Test
	public void testCountELem() {
		assertEquals(0, lista.countElem("A"));
		lista.addFirst("A");
		assertEquals(1, lista.countElem("A"));
		lista.addFirst("D");
		lista.addFirst("S");
		lista.addFirst("S");
		lista.addFirst("A");
		lista.addFirst("A");
		Assert.assertEquals("(A A S S D A )", lista.toString());
		assertEquals(3, lista.countElem("A"));
		assertEquals(2, lista.countElem("S"));
		assertEquals(1, lista.countElem("D"));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testAddPosException() {
		lista.addPos("A", 0);
	}	
	
	@Test
	public void testAddPos() {
		lista.addPos("A", 1);
		Assert.assertEquals("(A )", lista.toString());
		lista.addPos("B", 2);
		Assert.assertEquals("(A B )", lista.toString());
		lista.addPos("C", 2);
		Assert.assertEquals("(A C B )", lista.toString());
		lista.addPos("D", 3);
		Assert.assertEquals("(A C D B )", lista.toString());
		lista.addPos("Z", 4);
		Assert.assertEquals("(A C D Z B )", lista.toString());
		lista.addPos("3", 1);
		Assert.assertEquals("(3 A C D Z B )", lista.toString());
	}
	
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveFirstException() throws EmptyCollectionException {
		lista.removeFirst();
	}
	
	@Test()
	public void testRemoveFirst() throws EmptyCollectionException {
		lista.addFirst("2");
		assertEquals("(2 )", lista.toString());
		assertEquals("2", lista.removeFirst());
		assertEquals("()", lista.toString());
		
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D )", lista.toString());
		assertEquals("A", lista.removeFirst());
		
		assertEquals("(B C D )", lista.toString());
		assertEquals("B", lista.removeFirst());
		
		assertEquals("(C D )", lista.toString());
		assertEquals("C", lista.removeFirst());
		
		assertEquals("(D )", lista.toString());
		assertEquals("D", lista.removeFirst());
		
		assertEquals("()", lista.toString());
	}
	
	
	@Test(expected = EmptyCollectionException.class)
	public void testRemoveLastException() throws EmptyCollectionException {
		lista.removelast();
	}
	
	
	@Test()
	public void testRemoveLast() throws EmptyCollectionException {
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
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
	
	
	
	@Test (expected = NoSuchElementException.class)
	public void testRemoveElemException() throws EmptyCollectionException {
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D )", lista.toString());
		lista.removeElem("Z");
	}

		
	@Test 
	public void testRemoveElem() throws EmptyCollectionException {
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D A B C D )", lista.toString());
		assertEquals(1, lista.removeElem("A"));

		assertEquals("(B C D A B C D )", lista.toString());
		assertEquals(3, lista.removeElem("D"));
		assertEquals("(B C A B C D )", lista.toString());
		assertEquals(2, lista.removeElem("C"));
		assertEquals("(B A B C D )", lista.toString());
		assertEquals(5, lista.removeElem("D"));
		assertEquals("(B A B C )", lista.toString());
		assertEquals(4, lista.removeElem("C"));
		assertEquals("(B A B )", lista.toString());
		assertEquals(1, lista.removeElem("B"));
		assertEquals("(A B )", lista.toString());
		assertEquals(2, lista.removeElem("B"));
		assertEquals("(A )", lista.toString());
		assertEquals(1, lista.removeElem("A"));
		assertEquals("()", lista.toString());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testGetElemPosException() {
		lista.addFirst("H");
		lista.addFirst("G");
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E F G H )", lista.toString());
		lista.getElemPos(-9);
		lista.getElemPos(9);
		lista.getElemPos(0);
	}
	
	
	@Test
	public void testGetElemPos() {
		lista.addFirst("H");
		lista.addFirst("G");
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("D");
		lista.addFirst("C");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B C D E F G H )", lista.toString());
		assertEquals("A", lista.getElemPos(1));
		assertEquals("B", lista.getElemPos(2));
		assertEquals("C", lista.getElemPos(3));
		assertEquals("D", lista.getElemPos(4));
		assertEquals("E", lista.getElemPos(5));
		assertEquals("F", lista.getElemPos(6));
		assertEquals("G", lista.getElemPos(7));
		assertEquals("H", lista.getElemPos(8));
		
		assertEquals("H", lista.getElemPos(-1));
		assertEquals("G", lista.getElemPos(-2));
		assertEquals("F", lista.getElemPos(-3));
		assertEquals("E", lista.getElemPos(-4));
		assertEquals("D", lista.getElemPos(-5));
		assertEquals("C", lista.getElemPos(-6));
		assertEquals("B", lista.getElemPos(-7));
		assertEquals("A", lista.getElemPos(-8));
	}
	
	@Test
	public void testRemovePosLast() throws EmptyCollectionException {
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("F");
		lista.addFirst("E");
		lista.addFirst("B");
		lista.addFirst("A");
		lista.addFirst("B");
		lista.addFirst("A");
		assertEquals("(A B A B E F E F )", lista.toString());
		
		assertEquals(3, lista.removePosLast("A"));
		assertEquals("(A B B E F E F )", lista.toString());
		assertEquals(1, lista.removePosLast("A"));
		assertEquals("(B B E F E F )", lista.toString());
		assertEquals(6, lista.removePosLast("F"));
		assertEquals("(B B E F E )", lista.toString());
		assertEquals(4, lista.removePosLast("F"));
		assertEquals("(B B E E )", lista.toString());
		assertEquals(2, lista.removePosLast("B"));
		assertEquals("(B E E )", lista.toString());
		
		assertEquals(3, lista.removePosLast("E"));
		assertEquals("(B E )", lista.toString());
		assertEquals(2, lista.removePosLast("E"));
		assertEquals("(B )", lista.toString());
		assertEquals(1, lista.removePosLast("B"));
		assertEquals("()", lista.toString());
		
	}
	
	@Test
	public void testIntersec() {
		CircularDoubleWithHeaderList<String> lista2 = new CircularDoubleWithHeaderList<String>();
		
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
		
		CircularDoubleWithHeaderList<String> lista3 = new CircularDoubleWithHeaderList<String>();
		CircularDoubleWithHeaderList<String> lista4 = new CircularDoubleWithHeaderList<String>();
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
	public void testFromUntilIncludedStringException() {
		lista.fromUntilIncluded(0, 0);
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
	
	@Test(expected = IllegalArgumentException.class)
	public void testFromUntilIteratorException4() {
		Iterator<String>iter = lista.fromUntilIterator(-2, 6);
		
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

		
				
