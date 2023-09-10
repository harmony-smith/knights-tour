package code;


	public class LinkedGrid {
		private Node root;
		private int paths = 0;
		
		LinkedGrid(int dimension) {
			root = new Node();
			Node columnMarker = root;
			Node rowMarker = root;
			Node temp = null;
			
			//first row
			for(int x = 0; x < dimension - 1; x ++) {
				temp = new Node();
				columnMarker.setRight(temp);
				temp.setLeft(columnMarker);
				columnMarker = temp;
			}
			
			for (int y = 0; y < dimension - 1; y++) {
				//first node in row
				temp = new Node();
				columnMarker = temp;
				rowMarker.setDown(temp);
				temp.setUp(rowMarker);
				
				for (int x = 0; x < dimension - 1; x++) {
					temp = new Node();
					columnMarker.setRight(temp);
					temp.setLeft(columnMarker);
					temp.setUp(columnMarker.getUp().getRight());
					temp.getUp().setDown(temp);
					columnMarker = temp;
				}
				rowMarker = rowMarker.getDown();
			}
		}
		
		public void display() {
			Node temp = root;
			Node rowMarker = root;
			
			while (temp != null) {
				while (temp != null) {
					System.out.print(temp.getData() + " ");
					temp = temp.getRight();
				}
				System.out.println();
				temp = rowMarker.getDown();
				rowMarker = temp;
			}
		}
		
		public void flood (int newNum) {
			flood(root, newNum);
		}
		
		public void flood (Node current, int newNum) {
			int oldNum = current.getData();
			current.setData(newNum);
			
			if (current.getUp() != null && current.getUp().getData() == oldNum) {
				flood(current.getUp(), newNum);
			}
			if (current.getRight()!= null && current.getRight().getData() == oldNum) {
				flood(current.getRight(), newNum);
			}
			if (current.getDown() != null && current.getDown().getData() == oldNum) {
				flood(current.getDown(), newNum);
			}
			if (current.getLeft() != null && current.getLeft().getData() == oldNum) {
				flood(current.getLeft(), newNum);
			}
			
			//ANOTHER OPTION:
			/*
			 try {
			 	if (current.getUp().getData() == oldNum) {
			 		flood(current.getUp(), newNum);
			 	}
			  } catch (Exception) {};
			 */
		}
		
		public boolean isHomogenous() {
			Node temp = root;
			Node rowMarker = root;
			int num = root.getData();
			
			while (temp != null) {
				while (temp != null) {
					if (temp.getData() != num) {
						return false;
					}
					temp = temp.getRight();
				}
				temp = rowMarker.getDown();
				rowMarker = temp;
			}
			return true;
		}
		
		public void link() {
			Node temp = root;
			Node rowMarker = root;
			
			while (temp != null) {
				while (temp != null) {
					
					try {
					 	temp.setPath1(temp.getUp().getUp().getLeft());
					 	if (temp.getPath1() != null) {
					 		temp.setFreedom(temp.getFreedom() + 1);
					 	}
					} catch (Exception e) {};
					try {
						 temp.setPath2(temp.getUp().getUp().getRight());
						 if (temp.getPath2() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath3(temp.getRight().getRight().getUp());
						 if (temp.getPath3() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);	
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath4(temp.getRight().getRight().getDown());
						 if (temp.getPath4() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath5(temp.getDown().getDown().getRight());
						 if (temp.getPath5() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath6(temp.getDown().getDown().getLeft());
						 if (temp.getPath6() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath7(temp.getLeft().getLeft().getDown());
						 if (temp.getPath7() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					try {
						 temp.setPath8(temp.getLeft().getLeft().getUp());
						 if (temp.getPath8() != null) {
							 temp.setFreedom(temp.getFreedom() + 1);
						 }
					} catch (Exception e) {};
					temp = temp.getRight();
				}
				temp = rowMarker.getDown();
				rowMarker = temp;
			}
			//System.out.println(root.getFreedom());
			//System.out.println(root.getDown().getDown().getDown().getDown().getFreedom());
		}
		
		public void tour(int size) {
			paths = 0;
			tour(root, 1, size);
		}
		
		public void tour(Node node, int count, int size) {
			node.setData(count);
			boolean forcedPath = false;
			//System.out.println();
			//display();
			
			if (count < size*size) {
				//System.out.println(node.getFreedom());
				try {
					node.getPath1().setFreedom(node.getPath1().getFreedom() - 1);
					if (node.getPath1().getFreedom() == 0 && node.getPath1().getData() == 0) {
						forcedPath = true;
						tour(node.getPath1(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath2().setFreedom(node.getPath2().getFreedom() - 1);
					if (node.getPath2().getFreedom() == 0 && node.getPath2().getData() == 0) {
						forcedPath = true;
						tour(node.getPath2(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath3().setFreedom(node.getPath3().getFreedom() - 1);
					if (node.getPath3().getFreedom() == 0 && node.getPath3().getData() == 0) {
						forcedPath = true;
						tour(node.getPath3(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath4().setFreedom(node.getPath4().getFreedom() - 1);
					if (node.getPath4().getFreedom() == 0 && node.getPath4().getData() == 0) {
						forcedPath = true;
						tour(node.getPath4(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath5().setFreedom(node.getPath5().getFreedom() - 1);
					if (node.getPath5().getFreedom() == 0 && node.getPath5().getData() == 0) {
						forcedPath = true;
						tour(node.getPath5(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath6().setFreedom(node.getPath6().getFreedom() - 1);
					if (node.getPath6().getFreedom() == 0 && node.getPath6().getData() == 0) {
						forcedPath = true;
						tour(node.getPath6(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath7().setFreedom(node.getPath7().getFreedom() - 1);
					if (node.getPath7().getFreedom() == 0 && node.getPath7().getData() == 0) {
						forcedPath = true;
						tour(node.getPath7(), count + 1, size);
					}
				}  catch (Exception e) {};
				try {
					node.getPath8().setFreedom(node.getPath8().getFreedom() - 1);
					if (node.getPath8().getFreedom() == 0 && node.getPath8().getData() == 0) {
						forcedPath = true;
						tour(node.getPath8(), count + 1, size);
					}
				}  catch (Exception e) {};
				
				/*
				if (forcedPath == true) {
					System.out.println("forced path");	
				}
				*/
							
				if (forcedPath == false) {
					try {
						if (node.getPath1().getData() == 0) {
							tour(node.getPath1(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath2().getData() == 0) {
							tour(node.getPath2(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath3().getData() == 0) {
							tour(node.getPath3(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath4().getData() == 0) {
							tour(node.getPath4(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath5().getData() == 0) {
							tour(node.getPath5(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath6().getData() == 0) {
							tour(node.getPath6(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath7().getData() == 0) {
							tour(node.getPath7(), count + 1, size);
						}
					} catch (Exception e) {};
					try {
						if (node.getPath8().getData() == 0) {
							tour(node.getPath8(), count + 1, size);
						}
					} catch (Exception e) {};
				}
			} else {
				//System.out.println("Path found");
				//display();
				//System.out.println();
				paths++;
			}
			
			node.setData(0);
			upFreedom(node);
		}
		
		public int getPaths() {
			return paths;
		}

		public void setPaths(int paths) {
			this.paths = paths;
		}

		public Node getRoot() {
			return root;
		}
		
		public void upFreedom(Node node) {
			try {
				node.getPath1().setFreedom(node.getPath1().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath2().setFreedom(node.getPath2().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath3().setFreedom(node.getPath3().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath4().setFreedom(node.getPath4().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath5().setFreedom(node.getPath5().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath6().setFreedom(node.getPath6().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath7().setFreedom(node.getPath7().getFreedom() + 1);
			}  catch (Exception e) {};
			try {
				node.getPath8().setFreedom(node.getPath8().getFreedom() + 1);
			}  catch (Exception e) {};
		}
	}
