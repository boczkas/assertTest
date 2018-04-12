import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;


public class TestArrayList {

    private static List list;

    public static void main(String[]args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        for(Method method : Class.forName("TestArrayList").getDeclaredMethods()){
            if (!method.getName().equals("main")) {

                System.out.println(method.getName() + method.getReturnType());
                list = setupArrayList();
                method.invoke(method);

                list = setupLinkedList();
                method.invoke(method);
            }
        }
    }

    private static ArrayList setupArrayList() {
        return new ArrayList();
    }

    private static LinkedList setupLinkedList(){
        return new LinkedList();
    }

    private static void isEmpty_forEmptyList_returnsTrue(){
        // given

        // when

        // then
        assert list.isEmpty() : "isEmpty returns false for empty list";
    }

    private static void isEmpty_forNonEmptyList_returnsFalse(){
        // given
        list.add(1);
        // when

        // then
        assert !list.isEmpty(): "isEmpty returns true for non-empty list";
    }

    private static void isEmpty_addElementRemoveElement_returnsTrue(){
        // given

        // when
        list.add(1);
        list.remove(0);

        // then
        assert list.isEmpty() : "isEmpty returns false for empty list";
    }

    private static void contains_forEmptyList_returnsFalse(){
        // given
        Object object = new Object();
        // when

        // then
        assert !list.contains(object) : "contains returns true for empty list";
    }

    private static void contains_elementPresentInList_returnsTrue(){
        
    }
}
