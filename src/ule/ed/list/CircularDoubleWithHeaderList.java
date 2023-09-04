package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

//import ule.ed.list.DoubleLinkedList.DoubleNode;

//import ule.ed.list.DoubleLinkedList.DoubleNode;

public class CircularDoubleWithHeaderList<T> implements IDoubleNotOrderedList<T> {
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA

	// referencia al nodo cabecera : único nodo cuyo elem es null
	DoubleNode<T> header;

	private class DoubleNode<T> {

		DoubleNode(T element) {
			this.elem = element;
			this.next = null;
			this.prev = null;
		}

		T elem;

		DoubleNode<T> next;
		DoubleNode<T> prev;
	}

	// CLASE DEL ITERADOR NORMAL
	private class CircularDoubleList_Iterator<T> implements Iterator<T> {
		private int current;
		private DoubleNode<T> currentNode;

		private CircularDoubleList_Iterator(DoubleNode<T> aux) {
			this.currentNode = aux.next;
		}

		@Override
		public boolean hasNext() {
			// TODO
			return currentNode != header;
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T elem = currentNode.elem;
			currentNode = currentNode.next;
			return elem;
		}
	}

	/// TODO : AÑADIR OTRAS CLASES PARA LOS OTROS ITERADORES
	// CLASE DEL ITERADOR REVERSE
	private class CircularDoubleList_IteratorReverse<T> implements Iterator<T> {
		private int current;
		private DoubleNode<T> currentNode;

		private CircularDoubleList_IteratorReverse(DoubleNode<T> aux) {
			this.currentNode = aux.prev;
		}

		@Override
		public boolean hasNext() {
			// TODO
			return currentNode != header;

		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T elem = currentNode.elem;
			currentNode = currentNode.prev;
			return elem;

		}
	}

	// CLASE DEL ITERADOR FROM UNTIL
	private class CircularDoubleList_IteratorFromUntil<T> implements Iterator<T> {
		private int currentIndex;
		private DoubleNode<T> currentNode;
		private int from;
		private int until;

		private CircularDoubleList_IteratorFromUntil(int from, int until, DoubleNode<T> auxFront,
				DoubleNode<T> auxLast) {
			if (from == 0 || until == 0) {
				throw new IllegalArgumentException();
			}

			if (from > 0 && until > 0) {
				this.currentIndex = 1;
				this.from = from;
				this.until = until;
				currentNode = auxFront;

				while (currentIndex < from) {
					this.currentNode = this.currentNode.next;
					currentIndex++;
				}
			} else if(from < 0 && until < 0){
				this.currentIndex = -1;
				this.from = from;
				this.until = until;
				currentNode = auxLast;

				while (currentIndex > from) {
					this.currentNode = this.currentNode.prev;
					currentIndex--;
				}
			}else {
				throw new IllegalArgumentException();
			}

		}

		@Override
		public boolean hasNext() {
			// TODO
			if (from > 0) {
				return currentNode != header && currentIndex <= until;
			} else {
				return currentNode != header && currentIndex >= until;
			}
		}

		@SuppressWarnings("unchecked")
		@Override
		public T next() {
			// TODO
			if (!hasNext()) {
				throw new NoSuchElementException();
			}

			T elem = null;
			if (from > 0) {
				elem = currentNode.elem;
				currentNode = currentNode.next;
				currentIndex++;
			} else {
				elem = currentNode.elem;
				currentNode = currentNode.prev;
				currentIndex--;
			}
			return elem;

		}
	}

	// FIN ITERADORES
	//////////////////////

	// CONSTRUCTOR
	public CircularDoubleWithHeaderList() {
		// debe crear el nodo cabecera vacío (con elem igual a null)
		header = new DoubleNode<T>(null);
		header.prev = header;
		header.next = header;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		int size = 0;
		DoubleNode<T> aux = header.next;

		while (aux != header) {
			aux = aux.next;
			size++;
		}

		return size;

	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size() == 0;
	}

	@Override
	public void addFirst(T elem) {
		// TODO Auto-generated method stub
		this.checkNull(elem);
		DoubleNode<T> node = new DoubleNode<T>(elem);
		if (isEmpty()) {
			header.next = node;
			header.prev = node;
			node.prev = header;
			node.next = header;

		} else {
			node.next = header.next;
			node.prev = header;
			header.next.prev = node;
			header.next = node;
		}
	}

	@Override
	public void addLast(T elem) {
		// TODO Auto-generated method stub
		this.checkNull(elem);
		DoubleNode<T> node = new DoubleNode<T>(elem);

		if (isEmpty()) {
			this.addFirst(elem);
		} else {
			node.next = header;
			node.prev = header.prev;
			header.prev.next = node;
			header.prev = node;
		}
	}

	@Override
	public void addPos(T elem, int position) {
		// TODO Auto-generated method stub
		this.checkNull(elem);

		if (position <= 0) {
			throw new IllegalArgumentException("ERROR: La posicion no puede ser negativa");
		}

		if (position > size()) {
			this.addLast(elem);
		} else if (position == 1) {
			this.addFirst(elem);
		} else {
			DoubleNode<T> node = new DoubleNode<T>(elem);
			DoubleNode<T> aux = header.next;

			int posActual = 1;

			while (posActual < position - 1 && aux != header) {
				aux = aux.next;
				posActual++;
			}

			node.next = aux.next;
			node.prev = aux;

			aux.next.prev = node;
			aux.next = node;
		}
	}

	@Override
	public T removeFirst() throws EmptyCollectionException {

		this.checkIsEmpty();
		T elem = header.next.elem;
		header.next = header.next.next;
		header.next.prev = header;

		return elem;
	}

	@Override
	public T removelast() throws EmptyCollectionException {

		this.checkIsEmpty();

		T elem = header.prev.elem;

		header.prev.prev.next = header;
		header.prev = header.prev.prev;

		return elem;
	}

	@Override
	public int removeElem(T elem) throws EmptyCollectionException {
		this.checkNull(elem);
		this.checkIsEmpty();
		this.checkElemExists(elem);
		
		DoubleNode<T> aux = header.next;
		int pos = 1;
		int currentPos = 1;
		while (aux != header) {
			if (aux.elem.equals(elem)) {
				pos = currentPos;
				break;
			}
			aux = aux.next;
			currentPos++;
		}

		if (pos == 1) {
			this.removeFirst();
		} else if (pos == size()) {
			this.removelast();
		} else {
			aux = header.next;

			while (aux != header) {
				if (aux.next.elem.equals(elem)) {
					break;
				}
				aux = aux.next;
			}

			aux.next = aux.next.next;
			aux.next.prev = aux;
		}

		return pos;
	}

	@Override
	public T getElemPos(int position) {
		// TODO Auto-generated method stub

		if (position == 0 || position < -size() || position > size()) {
			throw new IllegalArgumentException("ERROR: Posicion incorrecta");
		}

		T elem = null;

		if (position > 0) {
			DoubleNode<T> aux = header.next;
			int pos = 1;
			while (pos < position) {
				aux = aux.next;
				pos++;
			}

			elem = aux.elem;
		} else {
			DoubleNode<T> aux = header.prev;
			int pos = -1;
			while (pos > position) {
				aux = aux.prev;
				pos--;
			}

			elem = aux.elem;

		}

		return elem;
	}

	@Override
	public int removePosLast(T elem) throws EmptyCollectionException {
		// TODO Auto-generated method stub
		this.checkNull(elem);
		this.checkIsEmpty();
		this.checkElemExists(elem);

		DoubleNode<T> aux = header.next;
		int currentPos = 1, posElem = 0;
		while (aux != header) {
			if (aux.elem.equals(elem)) {
				posElem = currentPos;
			}
			currentPos++;
			aux = aux.next;
		}

		if (posElem == 1) {
			this.removeFirst();
		} else if (posElem == size()) {
			this.removelast();
		} else {
			aux = header.next;
			int count = 1;

			while (count < posElem) {
				aux = aux.next;
				count++;
			}

			aux.prev.next = aux.next;
			aux.next.prev = aux.prev;
			aux.prev = null;
		}

		return posElem;
	}

	@Override
	public int countElem(T elem) {
		// TODO Auto-generated method stub
		this.checkNull(elem);
		DoubleNode<T> aux = header.next;
		int numElem = 0;

		while (aux != header) {
			if (aux.elem.equals(elem)) {
				numElem++;
			}
			aux = aux.next;
		}
		return numElem;
	}

	@Override
	public IDoubleNotOrderedList<T> intersec(IDoubleNotOrderedList<T> other) {
		// TODO Auto-generated method stub
		DoubleLinkedList<T> list = new DoubleLinkedList<T>();
		if (this.isEmpty() || other.isEmpty()) {
			return list;
		}

		DoubleNode<T> currentNode = header.next;

		while (currentNode != header) {
			T currentElem = currentNode.elem;

			if (list.countElem(currentElem) == 0) {
				int numOther = other.countElem(currentElem);
				int numThis = this.countElem(currentElem);
				int count = 0, min;

				if (numOther > numThis) {
					min = numThis;
				} else {
					min = numOther;
				}

				while (count < min) {
					list.addLast(currentElem);
					count++;
				}

			}

			currentNode = currentNode.next;
		}
		return list;
	}

	@Override
	public String fromUntilIncluded(int from, int until) {
		// TODO Auto-generated method stub
		StringBuilder sc = new StringBuilder();

		int position = from;

		if (from == 0 || until == 0) {
			throw new IllegalArgumentException("ERROR");
		} else if (from > 0 && until > 0) {
			DoubleNode<T> aux = header.next;
			if (from > this.size()) {
				sc.append("()");
			} else if (until > this.size()) {
				int auxCount = 1;

				while (auxCount < from) {

					aux = aux.next;
					auxCount++;
				}
				sc.append("(");
				while (aux != header && position <= until) {
					sc.append(aux.elem + " ");
					aux = aux.next;
					position++;
				}
				sc.append(")");
			} else {
				int auxCount = 1;

				while (auxCount < from) {

					aux = aux.next;
					auxCount++;
				}

				sc.append("(");
				while (position <= until) {
					sc.append(aux.elem + " ");
					aux = aux.next;
					position++;
				}
				sc.append(")");
			}
		} else if(from < 0 && until < 0) {
			DoubleNode<T> aux = header.prev;
			
			if (from < -this.size() + 1) {
				sc.append("()");
			} else if (until < -this.size() + 1) {
				int auxCount = -1;

				while (auxCount > from) {

					aux = aux.prev;
					auxCount--;
				}
				sc.append("(");
				while (aux != header && position >= until) {
					sc.append(aux.elem + " ");
					aux = aux.prev;
					position--;
				}
				sc.append(")");
			} else {
				int auxCount = -1;

				while (auxCount > from) {

					aux = aux.prev;
					auxCount--;
				}

				sc.append("(");
				while (position >= until && aux != header) {
					sc.append(aux.elem + " ");
					aux = aux.prev;
					position--;
				}
				sc.append(")");
			}
		}else {
			throw new IllegalArgumentException();
		}

		return sc.toString();

	}

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return new CircularDoubleList_Iterator<>(header);
	}

	@Override
	public Iterator<T> iteratorReverse() {
		// TODO Auto-generated method stub
		return new CircularDoubleList_IteratorReverse<>(header);
	}

	@Override
	public Iterator<T> fromUntilIterator(int from, int until) {
		// TODO Auto-generated method stub
		return new CircularDoubleList_IteratorFromUntil(from, until, header.next, header.prev);
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sc = new StringBuilder();
		sc.append("(");
		DoubleNode<T> aux = header.next;

		while (aux != header) {
			sc.append(aux.elem.toString() + " ");
			aux = aux.next;
		}

		sc.append(")");
		return sc.toString();

	}

	@Override
	public String toStringReverse() {
		// TODO Auto-generated method stub
		StringBuilder sc = new StringBuilder();
		sc.append("(");

		DoubleNode<T> aux = header.prev;
		while (aux != header) {
			sc.append(aux.elem.toString() + " ");
			aux = aux.prev;
		}
		sc.append(")");
		return sc.toString();

	}

	private void checkNull(T elem) {
		if (elem == null) {
			throw new NullPointerException("ERROR: El elemento no puede ser nulo");
		}
	}

	private void checkIsEmpty() throws EmptyCollectionException {
		if (isEmpty()) {
			throw new EmptyCollectionException("ERROR: La lista está vacía");
		}
	}

	private void checkElemExists(T elem) {
		DoubleNode<T> aux = header.next;
		int numElem = 0;

		while (aux != header) {
			if (aux.elem.equals(elem)) {
				numElem++;
			}
			aux = aux.next;
		}

		if (numElem == 0) {
			throw new NoSuchElementException("ERROR: EL ELEMENTO NO ESTÄ EN LA LISTA");
		}
	}
}
