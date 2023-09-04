package ule.ed.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoubleLinkedList<T> implements IDoubleNotOrderedList<T>{

	//	referencias al primer y al último nodo de la lista
	// la lista vacía debe tener front y last apuntando a null
	private DoubleNode<T> front;
	private DoubleNode<T> last;
	
	// NO SE PUEDEN AÑADIR MÁS ATRIBUTOS A LA LISTA
		 
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
		private class DoubleList_Iterator<T> implements Iterator<T> {
			private int current;
			private DoubleNode<T> currentNode;

			DoubleList_Iterator(DoubleNode<T> aux){
				currentNode = aux;
			}
			
			@Override
			public boolean hasNext() {
			//TODO
			return currentNode != null;
			
			}

			@SuppressWarnings("unchecked")
			@Override
			public T next() {
				//TODO
				
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			T elem = currentNode.elem;
			currentNode = currentNode.next;
			
			return elem;
			}
		}
		
		// CLASE DEL ITERADOR REVERSE 
				private class DoubleList_IteratorReverse<T> implements Iterator<T> {
					private int current;
					private DoubleNode<T> currentNode;

					DoubleList_IteratorReverse(DoubleNode<T> aux){
						currentNode = aux;
					}
					
					@Override
					public boolean hasNext() {
					//TODO
					return currentNode != null;
					
					}

					@SuppressWarnings("unchecked")
					@Override
					public T next() {
						//TODO
						
					if(!hasNext()) {
						throw new NoSuchElementException();
					}
					T elem = currentNode.elem;
					currentNode = currentNode.prev;
					
					return elem;
					}
				}
		
				private class DoubleList_IteratorFromUntil<T> implements Iterator<T> {
					private int currentIndex;
					private DoubleNode<T> currentNode;
				    private int from;
				    private int until;

					DoubleList_IteratorFromUntil(int from, int until, DoubleNode<T> auxFront, DoubleNode<T> auxLast){
						if(from == 0 || until == 0 ) {
							throw new IllegalArgumentException();
						}
						
						if(from > 0 && until > 0) {
							this.currentIndex = 1;
							this.from = from;
							this.until = until;
							currentNode = auxFront;
							
					        while (currentIndex < from) {
					            this.currentNode = this.currentNode.next;
					            currentIndex++;
					        }
						}else if (from < 0 && until < 0) {
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
					//TODO
					if(from > 0) {
						return currentNode != null && currentIndex <= until;
					}else {
						
						return currentNode != null && currentIndex >= until;
					}
					
					}

					@SuppressWarnings("unchecked")
					@Override
					public T next() {
						//TODO
						
					if(!hasNext()) {
						throw new NoSuchElementException();
					}
					
					T elem = null;
					if(from > 0) {
						elem = currentNode.elem;
						currentNode = currentNode.next;
						currentIndex++;
					}else {
						elem = currentNode.elem;
						currentNode = currentNode.prev;
						currentIndex--;
					}
					
					return elem;
					}
				}
				
		// FIN ITERADORES


		@Override
		public int size() {
			// TODO Auto-generated method stub
			int size = 0;
			DoubleNode<T> aux = front;
			
			while(aux != null) {
				aux = aux.next;
				size++;
			}
			
			return size;
		}

		@Override
		public boolean isEmpty() {
			// TODO Auto-generated method stub
			return (front == null && last == null);
			
	
		}

		@Override
		public void addFirst(T elem) {
			// TODO Auto-generated method stub
			this.checkNull(elem);
			DoubleNode<T> node = new DoubleNode<T>(elem);
			
			if(this.isEmpty()) {
				node.prev = front;
				front = node;
				last = node;
				node.next = null;
			}else {
				
				node.next = front;
				front.prev=node;
				node.prev = null;
				front = node;
			}
		}

		@Override
		public void addLast(T elem) {
			// TODO Auto-generated method stub
			this.checkNull(elem);
			if(this.isEmpty()) {
				this.addFirst(elem);
			}else {
				DoubleNode<T> node = new DoubleNode<T>(elem);
				last.next = node;
				node.prev = last;
				last = node;
			}
		}


		@Override
		public void addPos(T elem, int position) {
			// TODO Auto-generated method stub
			this.checkNull(elem);
			
			if(position <= 0) {
				throw new IllegalArgumentException("ERROR: POSICION INCORRECTA");
			}
			
			if(position > size()) {
				this.addLast(elem);
			}else {
				if(position == 1) {
					DoubleNode<T> node = new DoubleNode<T>(elem);
					node.next = front;
					front.prev = node;
					front = node;
			
				}else {
					DoubleNode<T> node = new DoubleNode<T>(elem);
					DoubleNode<T> aux = front;
					int currentPos = 1;
					while(currentPos < position-1) {
						currentPos++;
						aux = aux.next;
					}
					aux.next.prev = node;
					node.next = aux.next;
					node.prev = aux;
					aux.next = node;
				}
			}
		}
		


		@Override
		public T removeFirst() throws EmptyCollectionException {
			// TODO Auto-generated method stub
			this.checkIsEmpty();
			T elem = null;
			
			if(front.next == null) {
				elem = front.elem;
				this.front = null;
				this.last = null;
			}else{
				elem = front.elem;
				this.front = front.next;
				this.front.prev = null;
			}
			
			return elem;
		}


		@Override
		public T removelast() throws EmptyCollectionException {
			// TODO Auto-generated method stub
			this.checkIsEmpty();
			T elem = null;
			if(front.next == null) {
				elem = this.removeFirst();
			}else{
				elem = this.last.elem;
				this.last = this.last.prev;
				this.last.next = null;
			}
			
			return elem;
		}


		@Override
		public int removeElem(T elem) throws EmptyCollectionException {
			// TODO Auto-generated method stub
			this.checkIsEmpty();
			this.checkNull(elem);
			this.checkElemExists(elem);
			
			DoubleNode<T> aux = front;
			int pos = 1;
			int currentPos = 1;
			while(aux != null) {
				if(aux.elem.equals(elem)) {
					pos = currentPos;
					break;
				}
				aux = aux.next;
				currentPos++;
			}
			
			if(pos == 1) {
				this.removeFirst();
			}else if(pos == size()) {
				this.removelast();
			}else {
				
				aux = front;
				
				while(aux != null) {
					if(aux.elem.equals(elem)) {
						break;
					}
					aux = aux.next;
				}			
				
				aux.prev.next = aux.next;
				aux.next.prev = aux.prev;
				
			}
			
			return pos;
			
		}


		@Override
		public T getElemPos(int position) {
			// TODO Auto-generated method stub
			if(position == 0 ||position < ((-1) *size()) ||position > size()) {
				throw new IllegalArgumentException("ERROR: Posicion incorrecta");
			}
			T elem = null;
			if(position > 0) {
				
				DoubleNode<T> aux = front;
				int pos = 1;
			
				while(pos < position) {
					aux = aux.next;
					pos++;
				}
				
				elem = aux.elem;
				
				
			}else {
				
				DoubleNode<T> aux = last;
				int pos = -1;
			
				while(pos > position) {
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
			this.checkIsEmpty();
			this.checkNull(elem);
			this.checkElemExists(elem);
			
			DoubleNode<T> aux = front;
			int currentPos = 0;
			int posElem = 0;
			
			while(aux != null) {
				if(aux.elem.equals(elem)) {
					posElem = currentPos+1;
				}
				aux = aux.next;
				currentPos++;
			}
			
			if(posElem == 1) {
				this.removeFirst();
			}else if(posElem == size()) {
				this.removelast();
			}else {
				aux = front;
				int count = 1;
				
				while(count < posElem) {
					aux = aux.next;
					count++;
				}
				
				aux.prev.next = aux.next;
				aux.next.prev = aux.prev; 
			}
			return posElem;
		
		}


		@Override
		public int countElem(T elem) {
			// TODO Auto-generated method stub
			this.checkNull(elem);
			int count = 0;
			DoubleNode<T> aux = front;
			
			while(aux != null) {
				if(aux.elem.equals(elem)) {
					count++;
				}
				aux = aux.next;
			}
			return count;
		}


		@Override
		public IDoubleNotOrderedList<T> intersec(IDoubleNotOrderedList<T> other){
			DoubleLinkedList<T> list = new DoubleLinkedList<T>();
			if (this.isEmpty()||other.isEmpty()) {
				return list;
			}
			
		    DoubleNode<T> currentNode = front;
			
		    while(currentNode != null) {
		    	T currentElem = currentNode.elem;
		    	
		    	if(list.countElem(currentElem) == 0) {
		    		int numOther = other.countElem(currentElem);
		    		int numThis = this.countElem(currentElem);
		    		int count = 0, min;
		    		
		    		if(numOther > numThis) {
		    			min = numThis;
		    		}else {
		    			min = numOther;
		    		}
		    		
		    		while(count < min) {
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
			
			if(from == 0 || until == 0) {
				throw new IllegalArgumentException("ERROR");
			}
			else if(from > 0 && until > 0) {
				DoubleNode<T> aux = front;
				if(from > this.size()) {
					sc.append("()");
				}
				else if(until > this.size()) {
					int auxCount = 1;
					
					while(auxCount < from) {
						
						aux = aux.next;
						auxCount++;
					}
					sc.append("(");
					while(aux != null && position <= until) {
						sc.append(aux.elem + " ");
						aux = aux.next;
						position++;
					}
					sc.append(")");
				}else {
					int auxCount = 1;
					
					while(auxCount < from) {
						
						aux = aux.next;
						auxCount++;
					}
					
					sc.append("(");
					while(position <= until) {
						sc.append(aux.elem + " ");
						aux = aux.next;
						position++;
					}
					sc.append(")");
				}
			}else if(from < 0 && until < 0) {
				DoubleNode<T> aux = last;
				if(from < -this.size() + 1) {
					sc.append("()");
				}
				else if(until < -this.size() +1) {
					int auxCount = -1;
					
					while(auxCount > from) {
						
						aux = aux.prev;
						auxCount--;
					}
					sc.append("(");
					while(aux != null && position >= until) {
						sc.append(aux.elem + " ");
						aux = aux.prev;
						position--;
					}
					sc.append(")");
				}else {
					int auxCount = -1;
					
					while(auxCount > from) {
						
						aux = aux.prev;
						auxCount--;
					}
					
					sc.append("(");
					while(position >= until && aux != null) {
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
			return new DoubleList_Iterator<>(front);
		}


		@Override
		public Iterator<T> iteratorReverse() {
			// TODO Auto-generated method stub
			return new DoubleList_IteratorReverse<>(last);
		}


		@Override
		public Iterator<T> fromUntilIterator(int from, int until) {
			// TODO Auto-generated method stub
			
			
			return new DoubleList_IteratorFromUntil(from, until, front, last);
		}

		@Override
		public String toString() {
			StringBuilder sc = new StringBuilder();
			sc.append("(");
			DoubleNode<T>aux = front;
			
			while(aux != null) {
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
			
			DoubleNode<T> aux = last;
			while (aux != null) {
				sc.append(aux.elem.toString() + " ");
				aux = aux.prev;
			}
			sc.append(")");
			return sc.toString();
		}

		
		private void checkNull(T elem) {
			if(elem == null) {
				throw new NullPointerException("ERROR: El elemento no puede ser nulo");
			}
		}

		private void checkIsEmpty() throws EmptyCollectionException {
			if(isEmpty()) {
				throw new EmptyCollectionException("ERROR: La lista está vacía");
			}
		}
		
		private void checkElemExists(T elem) {
			
			DoubleNode<T> aux = front;
			int numElem = 0;
			
			while(aux != null) {
				if(aux.elem.equals(elem)) {
					numElem++;
				}
				aux = aux.next;
			}
			
			if(numElem == 0) {
				throw new NoSuchElementException("ERROR: EL ELEMENTO NO ESTÄ EN LA LISTA");
			}
		}
}

	