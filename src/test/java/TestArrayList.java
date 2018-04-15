import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static java.util.Arrays.sort;
import static java.util.Arrays.stream;


public class TestArrayList {

    private static List list;

    public static void main(String[]args) throws ClassNotFoundException, InvocationTargetException, IllegalAccessException {

        Stream<Method> methodStream = Arrays.stream(Class.forName("TestArrayList").getDeclaredMethods());

        methodStream
                .filter(method -> !method.getName().equals("main"))
                .filter(method -> !method.getName().startsWith("lambda"))
                .filter(method -> method.getReturnType().equals(Void.TYPE))
                .forEach(invokeMethod());

//        for(Method method : Class.forName("TestArrayList").getDeclaredMethods()){
//            if (!method.getName().equals("main")) {
//
//                System.out.println(method.getName() + method.getReturnType());
//                list = setupArrayList();
//                method.invoke(method);
//
//                list = setupLinkedList();
//                method.invoke(method);
//            }
//        }
    }

    private static Consumer<Method> invokeMethod() {
        return method -> {
            try {
                list = setupArrayList();
                method.invoke(method);
                list = setupLinkedList();
                method.invoke(method);
                System.out.println(method);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        };
    }

    private static ArrayList setupArrayList() {
        return new ArrayList();
    }

    private static LinkedList setupLinkedList(){
        return new LinkedList();
    }

    private static void size_forEmptyList_returns_0(){
        // given

        // when

        // then
        assert list.size() == 0 : "size returns value different than 0 for empty list";
    }

    private static void size_forListWithOneElement_returns_1(){
        // given

        // when

        // then
        assert true: "size returns value different than 1 for empty list";
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

    private static void isEmpty_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.isEmpty();
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for isEmpty() call on null object";
        }
    }

    private static void contains_forEmptyList_returnsFalse(){
        // given
        Object object = new Object();
        // when

        // then
        assert !list.contains(object) : "contains returns true for empty list";
    }

    private static void contains_elementPresentInList_returnsTrue(){
        // given
        String element = "Przemek";
        // when
        list.add(element);
        // then
        assert list.contains(element): "contains returns false when element is in the list";
    }

    private static void contains_elementNotPresentInList_returnsFalse(){
        // given
        String element = "Przemek";
        // when
        list.add(element);
        // then
        assert !list.contains(new String("Jakubowski")): "contains returns true when element is not in the list";
    }

    private static void contains_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;
        String element = "element";
        // when
        try{
            list.contains(element);
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for contains() call on null object";
        }
    }

    private static void iterator_forListContainingOneElement_returnsIteratorWithOneElement(){
        // given
        String element = "element";
        list.add(element);
        Iterator<String> iterator = list.iterator();

        // when
        String elementToCheck = iterator.next();
        boolean hasNext = iterator.hasNext();

        // then
        assert elementToCheck.equals(element) && hasNext == false: "iterator() returns Iterator with more than one element" +
                " for list which contains only one element";
    }

    private static void iterator_forEmptyList_returnsIteratorWithoutElements(){
        // given
        Iterator<String> iterator = list.iterator();

        // when

        // then
        assert iterator.hasNext() == false: "iterator() returns Iterator with element(s) for empty list";
    }

    private static void iterator_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.iterator();
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for iterator() call on null object";
        }
    }

    private static void toArray_forListWith_3_elements_returnsArrayWith_3_elements(){
        // given
        list.add("Java");
        list.add("Academy");
        list.add("Course");

        // when
        Object[] array = list.toArray();

        // then
        assert array.length == 3 : "toArray() returns array with different number elements than a list contains";
    }

    private static void toArray_forListWithoutElements_returnsArrayWithoutElements(){
        // given

        // when
        Object[] array = list.toArray();

        // then
        assert array.length == 0 : "toArray() returns array with elements for list without elements";
    }

    private static void toArray_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.toArray();
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for toArray() call on null object";
        }
    }

    /////// Dopytac: <T> T[] toArray(T[] a);

    private static void add_3_ElementsAdded_sizeOfListIsEqualTo_3(){
        // given
        list.add("Java");
        list.add("Academy");
        list.add("Course");

        // when

        // then
        assert list.size() == 3 : "After add() call for 3 elements size of array is different than 3";
    }

    private static void add_elementAdded_listContainsElement(){
        // given

        // when
        list.add("Przemek");

        // then
        assert list.contains("Przemek"): "After add() of element contains does not return true for it";
    }

    private static void add_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.add(new String("Przemek"));
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for add() call on null object";
        }
    }

    private static void remove_listContainsElement_removesElementFromList(){
        // given
        String element = "element";
        list.add(element);

        // when
        list.remove(element);

        // then
        assert list.contains(element) == false : "After remove() call for element existing in the list (present once) " +
                "it is still in the list";
    }

    private static void remove_listDoesNotContainsElement_listIsNotChanged(){
        // given
        String element1 = "element1";
        String element2 = "element2";

        // when
        list.add(element1);
        list.remove(element2);

        // then
        assert list.size() == 1 : "remove() changed the list even there was no element to be removed";
    }

    private static void remove_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.remove(new String("Przemek"));
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for remove() call on null object";
        }
    }

    private static void containsAll_listContainsElements_returnsTrue(){
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Academy");
        arrayList.add("Course");

        // when
        list.add("Java");
        list.add("Academy");
        list.add("Course");

        // then
        assert list.containsAll(arrayList) : "containsAll() returns false even when all of elements are present in the list";
    }

    private static void containsAll_listDoesNotContainsElements_returnsFalse(){
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Java");
        arrayList.add("Academy");
        arrayList.add("Course");

        // when
        list.add("Java");
        list.add("Academy");

        // then
        assert !list.containsAll(arrayList) : "containsAll() returns true when not all of elements are present in the list";
    }

    private static void containsAll_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try{
            list.containsAll(new ArrayList<>());
        }
        catch (Exception e){
            // then
            assert e.getClass().equals(NullPointerException.class) : "Exception different than null pointer exception" +
                    " has been thrown for containsAll() call on null object";
        }
    }

    private static void addAll_ads_listWith_3_elementsToList_sizeIs_3(){
        // given

        // when

        // then
        assert : "";
    }
}
