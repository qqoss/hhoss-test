package com.hhoss.test;


public class Resource implements AutoCloseable {  
	 
    private Resource() {  
        System.out.println("Opening resource");  
    }  


    public void prepare() {  
        System.out.println("Operating on resource");  
    }  

    //execute perform process operate
    public void operate() {  
        System.out.println("Operating on resource");  
    }  
 
    public void dispose() {  
        System.out.println("Disposing resource");  
    }  

	@Override
	public void close() throws Exception {
		// TODO Auto-generated method stub
		
	}

    public static void withResource(Consumer<Resource> consumer) {  
         
        try(Resource resource = new Resource(); ) {  
            consumer.accept(resource);  
        } catch (Exception e) {
			e.printStackTrace();
		}
    } 
    
    class Consumer<T> {

		public void accept(T res) {
			
		}

    }
    
    public static void main(String[] args){
	    String[] a[] = {{}};
	    
	    Integer i =  null ;   
	   	Double d =  2d;   
	    Object o =  1>0  ? i : d;  // NullPointerException!    
	    //如果『需要』，条件运算符会做数值类型的类型提升，
 
    }


} 