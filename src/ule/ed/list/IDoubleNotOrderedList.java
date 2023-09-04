package ule.ed.list;
import java.util.Iterator;

public interface IDoubleNotOrderedList<T> {
	/**
	 * TAD 'INotOrderedList'
	 * 
	 * Almacena una colección de objetos de tipo <code>T</code>, permitiendo
	 * elementos repetidos.
	 * 
	 * Ejemplo: (A B C A B D )
	 *
	 * 
	 * Excepciones
	 * 
	 * No se permiten elementos <code>null</code>. Si a cualquier método que recibe
	 * un elemento se le pasa el valor <code>null</code>, lanzará una excepción
	 * {@link NullPointerException}.
	 * 
	 * Los valores de parámetros <code>position</code> deben ser mayores que cero y
	 * nunca negativos. Si se recibe un valor negativo o cero se lanzará
	 * {@link IllegalArgumentException}.
	 * 
	 * 
	 * Constructores
	 * 
	 * Se definirá un constructor por defecto que inicialice la instancia como lista
	 * vacía.
	 * 
	 * 
	 * Método {@link Object#toString()}
	 * 
	 * El formato será mostrar el toString de los elementos separados por espacios
	 * (A B C D D D B ) el toString
	 *
	 * 
	 * @author profesor
	 *
	 * @param <T> tipo de elementos en la lista
	 */

	/**
	 * Devuelve el número total de elementos en esta lista. <br>
	 * 
	 * Ejemplo:<br>
	 * Si una lista l contiene (A B C B D A B ): <br>
	 * l.size() -> 7
	 * 
	 * @return número total de elementos en esta lista
	 */
	public int size();

	/**
	 * Indica si esta lista está vacía
	 * 
	 * @return <code>true</code> si no contiene elementos
	 */
	public boolean isEmpty();


	/**
	 * Añade un elemento como primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addFirst("C") la lista quedará
	 * (C A B C )
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addFirst(T elem);

	/**
	 * Añade un elemento como último elemento de la lista
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addLast("C") la lista quedará (A
	 * B C C )
	 * 
	 * @param elem el elemento a añadir
	 * 
	 * @throws NullPointerException si elem es <code>null</code>
	 */
	public void addLast(T elem);

	
	/**
	 * Añade un elemento en la posición pasada como parámetro desplazando los
	 * elementos que estén a partir de esa posición.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.addPos("Z", 2) la lista quedará
	 * (A Z B C ).
	 * <p>
	 * Si position>size() se insertará como último elemento.
	 * 
	 * @param elem     el elemento a añadir
	 * @param position la posición en la que añadirá el elemento
	 * 
	 * @throws NullPointerException     si elem es <code>null</code>
	 * @throws IllegalArgumentException si position <= 0
	 * 
	 */
	public void addPos(T elem, int position);

	/**
	 * Elimina y devuelve el primer elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeFirst() la lista quedará
	 * (B C ) y devolverá A
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * 
	 */
	public T removeFirst() throws EmptyCollectionException;

	/**
	 * Elimina y devuelve el último elemento de la lista.
	 * <p>
	 * Si una lista l contiene (A B C ) y hacemos l.removeLast() la lista quedará (A
	 * B ) y devolverá C
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * 
	 */
	public T removelast() throws EmptyCollectionException;;

	
	/**
	 * Elimina la primera aparición del elemento y devuelve la posición en la que estaba.
	 * <p>
	 * Si una lista l contiene (A B C B ) y hacemos l.removeElem("B") la lista
	 * quedará (A C B ) y devolverá 2
	 * 
	 * @param elem el elemento a eliminar
	 * @return posicion donde estaba el elemento
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NullPointerException   si elem es <code>null</code
	 * @throws NoSuchElementException   si la lista no contiene el elemento
	 * 
	 */
	public int removeElem(T elem) throws EmptyCollectionException;

	/**
	 * Devuelve el elemento que está en position.
	 * Position puede ser negativ, en ese caso se empezaría a contar por el final.
	 * <p>
	 * Si una lista l contiene (A B C D E ): <br>
	 * l.getElemPos(1) -> A <br>
	 * l.getElemPos(3) -> C <br>
	 * l.getElemPos(10) -> IllegalArgumentException
	 * l.getElemPos(-1)  -> E 
	 * l.getElemPos(-2)  -> D
	 * 
	 * 
	 * @param position: posición a comprobar para devolver el elemento.  
	 * 
	 * @throws IllegalArgumentException si position es cero 
	 *           o es menor que -size() o mayor que size()
	 * 
	 */
	public T getElemPos(int position);

	
	/**
	 * Devuelve la posición de la última aparición del elemento.
	 * <p>
	 * Si una lista l contiene (A B C B D A ): <br>
	 * l.getPosLast("A") -> 6 <br>
	 * l.getPosLast("B") -> 4 <br>
	 * l.getPosLast("Z") -> NoSuchElementException
	 * 
	 * @param elem elemento a encontrar.
	 * 
	 * @throws EmptyCollectionException si la lista es vacía
	 * @throws NullPointerException   si elem es <code>null</code>
	 * @throws NoSuchElementException si elem no está en la lista.
	 * 
	 */
	public int removePosLast(T elem) throws EmptyCollectionException;;;

	/**
	 * Devuelve el número de repeticiones del elemento que hay en la lista.
	 * <p>
	 * Si una lista l contiene (A B A C B D A ): <br>
	 * l.countElem("A") -> 3 <br>
	 * l.countElem("B") -> 2 <br>
	 * l.countElem("Z") -> 0 <br>
	 * 
	 * @param elem elemento a contar.
	 * @return lista intersección de ambas listas

	 * @throws NullPointerException   si elem es <code>null</code>
	 * 
	 */
	public int countElem(T elem);

	/**
	 * Crea una nueva lista con todos los elementos de la lista this que también estén en la lista other.
	 * Aparecerán en el mismo orden en el que están colocados en this. 
	 * El número de instancias de cada elemento será el número de instancias que haya en la lista que menos tenga.
	 * Las listas this y other debe quedar en el mismo estado.
	 * <p>
	 * Si una lista lista1 contiene (A B C B D A B ) y 
	 * otra lista  lista2 contiene (Z B A B A K A ): <br>
	 * lista1.instersec(lista2) devolverá la lista -> (A A B B ).
	 * 
	 * Si una lista lista1 contiene (A B C B D A B ) y 
	 * otra lista  lista2 contiene (Z L K ): <br>
	 * lista1.instersec(lista2) devolverá la lista vacía ya que no tienen ningún elemento en común.
	 * 
	 * Si alguna de las listas es vacía, devolverá una lista vacía.
	 * 
	 * @param elem elemento a eliminar.
	 * @return lista intersección de ambas listas
	 * @throws EmptyCollectionException 

	 */
	public IDoubleNotOrderedList<T> intersec(IDoubleNotOrderedList<T> other) throws EmptyCollectionException;

	
	/**
	 * Devuelve una cadena con los elementos comprendidos entre las posiciones from
	 * y until INCLUIDAS.
	 * La lista actual tiene que quedar en el mismo estado.
	 * from y until no pueden tomar valores 0 y los dos parámetros tienen que ser del mismo signo.
	 * Dispara la excepción IllegalArgumentExceotion si :
	 *    - from o until toman valores 0
	 *    - from y until tienen distinto signo.
	 * Si son positivos se considera el recorrido desde el primer elemento hacia el siguiente:
	 *   si from >until -> el iterador no devolverá ningún elemento
	 *   si until>size() -> devuelve hasta el final de la lista
	 * Si son negativos se considera el recorrido desde el último elemento hacia el anterior, por lo que:
	 *   si from <until -> devuelve la cadena vacía
	 *   si until< -size() -> devuelve hasta el final de la lista
	 *  
	 * <br>
	 * Ejemplos:<br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(1,3) -> (A B C ) <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(-2,-4) -> (D C B ) <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(-1,-10) -> (E D C B A ) <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(20,10) -> () <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(-20,-10) -> () <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(4,10) -> (D E ) <br>
	 * l1=(A B C D E ) ; l1.fromUntilIncluded(-4,-10) -> (B A ) <br>
	 * 
	 * 
	 * @param from  posición desde la que se empieza a considerar la lista
	 *              (incluida)
	 * @param until posición hasta la que se incluyen elementos (incluida)
	 * 
	 * @return cadena formada por el tosString() de los  elementos en el rango 
	 *          establecido por los dos parámetros (AMBOS INCLUIDOS).
	 * 
	 * @throws IllegalArgumentException   si from o until toman valores 0
	 *    o si toman distinto signo
	 *
	 */
	public String fromUntilIncluded(int from, int until);

	/**
	 * Devuelve un iterador que recorre la lista desde el primero hasta el último
	 * elemento. <br>
	 *
	 * Por ejemplo, para una lista x con elementos (A B C D E)
	 *
	 * el iterador creado con x.iterator() devuelve en sucesivas llamadas a next():
	 * A, B, C, D y E.
	 *
	 * @return iterador .
	 */
	public Iterator<T> iterator();
	
	/**
	 * Devuelve un iterador que recorre la lista desde el último hasta el primero
	 * elemento. <br>
	 *
	 * Por ejemplo, para una lista x con elementos (A B C D E)
	 *
	 * el iterador creado con x.iterator() devuelve en sucesivas llamadas a next():
	 * E, D, C, B y A.
	 *
	 * @return iterador .
	 */
	public Iterator<T> iteratorReverse();

	
	/**
	 * Devuelve un iterador que recorre los elementos que ocupan posiciones desde from 
	 * hasta until (ambas incluidas) en la lista.
	 * 
	 * Dispara la excepción IllegalArgumentExceotion si :
	 *    - from o until toman valores 0
	 *    - from y until tienen distinto signo.
	
	 * 
	 * Por ejemplo, para una lista x con elementos (1 2 3 4 5 6 7 8 9 )
	 * 
	 * el iterador creado con x.fromUntilIterator(3,6) devuelve en sucesivas llamadas a
	 * next(): 3, 4, 5, 6 
	 *
	 * @param from  posición desde la que se empieza a considerar la lista (incluida)
	 * @param until posición hasta la que se incluyen elementos (incluida)
	 * @return iterador para recorrer los elementos de la lista comprendidos
     *        entre las posiciones from y until.
     * 
     * @throws IllegalArgumentException   si from o until toman valores 0
	 *    o si toman distinto signo
	 */
	public Iterator<T> fromUntilIterator(int from, int until);
	
	/**
	 * Devuelve una cadena de texto con el contenido de la lista 
	 * desde el primero hasta el último
	 * 
	 * Por ejemplo, para una lista x con elementos (1 2 3 4 5 6 7 8 9 )
	 * x.toString() devolverá la cadena"(1 2 3 4 5 6 7 8 9 )"
	 *
	 * @return una cadena de texto con el contenido de la lista 
	 * desde el primero hasta el último
     * 
     */
	public String toString();
	
	/**
	 * Devuelve una cadena de texto con el contenido de la lista 
	 * desde el último hasta el primero
	 * 
	 * Por ejemplo, para una lista x con elementos (1 2 3 4 )
	 * x.toStringReverse() devolverá la cadena"(4 3 2 1 )"
	 *
	 * @return una cadena de texto con el contenido de la lista 
	 * desde el primero hasta el último
     * 
     */
	public String toStringReverse();
		
}
