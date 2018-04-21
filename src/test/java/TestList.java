import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Stream;


public class TestList {

    private static List list;
    private static GentleAssert test;

    public static void main(String[] args){
        test = new GentleAssert();
        Stream<Method> methodStream = Arrays.stream(TestList.class.getDeclaredMethods());

        methodStream
                .filter(method -> method.getAnnotations().length != 0)
                .filter(method -> method.getAnnotations()[0].annotationType().getName().equals(Test.class.getName()))
                .forEach(TestList::invokeMethod);

        test.showReport();
    }

    public static void invokeMethod(Method method) {
            try {
                list = setupArrayList();
                method.invoke(method);
                list = setupLinkedList();
                method.invoke(method);
//                System.out.println(method);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
    }

    private static ArrayList setupArrayList() {
        return new ArrayList();
    }

    private static LinkedList setupLinkedList() {
        return new LinkedList();
    }

    private static String createElement() {
        return "element";
    }

    private static String createElement(String string){
        return string;
    }

    private static ArrayList<String> createArrayListWith3Elements(){
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList("Java", "Academy", "Course"));
        return arrayList;
    }

    private static List<String> createListWith2Elements() {
        return Arrays.asList("Przemek", "Jakubowski");
    }

    private static String createDifferentElement() {
        return "Different";
    }

    @Test
    private static void size_forEmptyList_returns_0() {
        // given

        // when

        // then
        test.assertTrue(list.size() == 0, "size() returns value different than 0 for empty list");

    }

    @Test
    private static void size_forListWithOneElement_returns_1() {
        // given

        // when
        list.add(createElement());

        // then
        test.assertTrue(list.size() == 1,"size() returns value different than 1 for empty list");
    }

    @Test
    private static void isEmpty_forEmptyList_returnsTrue() {
        // given

        // when

        // then
        test.assertTrue(list.isEmpty(), "isEmpty() returns false for empty list");
    }

    @Test
    private static void isEmpty_forNonEmptyList_returnsFalse() {
        // given

        // when
        list.add(createElement());

        // then
        test.assertFalse(list.isEmpty(), "isEmpty returns true for non-empty list");
    }

    @Test
    private static void isEmpty_addElementRemoveElement_returnsTrue() {
        // given

        // when
        list.add(createElement());
        list.remove(0);

        // then
        test.assertTrue(list.isEmpty(), "isEmpty returns false for empty list");
    }

    @Test
    private static void isEmpty_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.isEmpty();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for isEmpty() call on null object");
        }
    }

    @Test
    private static void contains_forEmptyList_returnsFalse() {
        // given

        // when

        // then
        test.assertFalse(list.contains(createElement()), "contains returns true for empty list");
    }

    @Test
    private static void contains_elementPresentInList_returnsTrue() {
        // given

        // when
        list.add(createElement());

        // then
        test.assertTrue(list.contains(createElement()), "contains returns false when element is in the list");
    }

    @Test
    private static void contains_elementNotPresentInList_returnsFalse() {
        // given

        // when
        list.add(createElement());

        // then
        test.assertFalse(list.contains(createDifferentElement()), "contains returns true when element is not in the list");
    }

    @Test
    private static void contains_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.contains(createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for contains() call on null object");
        }
    }

    @Test
    private static void iterator_forListContainingOneElement_returnsIteratorWithOneElement() {
        // given
        list.add(createElement());
        Iterator<String> iterator = list.iterator();

        // when
        String elementToCheck = iterator.next();
        boolean hasNext = iterator.hasNext();

        // then
        test.assertTrue(elementToCheck.equals(createElement()) && hasNext == false , "iterator() returns Iterator with more than one element" +
                " for list which contains only one element");
    }

    @Test
    private static void iterator_forEmptyList_returnsIteratorWithoutElements() {
        // given
        Iterator<String> iterator = list.iterator();

        // when

        // then
        test.assertFalse(iterator.hasNext(), "iterator() returns Iterator with element(s) for empty list");
    }

    @Test
    private static void iterator_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.iterator();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for iterator() call on null object");
        }
    }

    @Test
    private static void toArray_forListWith_3_elements_returnsArrayWith_3_elements() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        Object[] array = list.toArray();

        // then
        test.assertTrue(array.length == 3, "toArray() returns array with different number elements than a list contains");
    }

    @Test
    private static void toArray_forListWithoutElements_returnsArrayWithoutElements() {
        // given

        // when
        Object[] array = list.toArray();

        // then
        test.assertTrue(array.length == 0, "toArray() returns array with elements for list without elements");
    }

    @Test
    private static void toArray_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.toArray();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for toArray() call on null object");
        }
    }

    /////// Dopytac: <T> T[] toArray(T[] a);

    @Test
    private static void add_3_ElementsAdded_sizeOfListIsEqualTo_3() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when

        // then
        test.assertTrue(list.size() == 3, "After add() call for 3 elements size of array is different than 3");
    }

    @Test
    private static void add_elementAdded_listContainsElement() {
        // given

        // when
        list.add(createElement());

        // then
        test.assertTrue(list.contains(createElement()), "After add() of element contains does not return true for it");
    }

    @Test
    private static void add_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.add(createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for add() call on null object");
        }
    }

    @Test
    private static void remove_listContainsElement_removesElementFromList() {
        // given
        list.add(createElement());

        // when
        list.remove(createElement());

        // then
        test.assertFalse(list.contains(createElement()), "After remove() call for element existing in the list (present once) " +
                "it is still in the list");
    }

    @Test
    private static void remove_listDoesNotContainsElement_listIsNotChanged() {
        // given

        // when
        list.add(createElement());
        list.remove(createDifferentElement());

        // then
        test.assertTrue( list.size() == 1, "remove() changed the list even there was no element to be removed");
    }

    @Test
    private static void remove_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.remove(createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for remove() call on null object");
        }
    }

    @Test
    private static void containsAll_listContainsElements_returnsTrue() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());

        // when
        list.addAll(createArrayListWith3Elements());

        // then
        test.assertTrue(list.containsAll(arrayList), "containsAll() returns false even when all of elements are present in the list");
    }

    @Test
    private static void containsAll_listDoesNotContainsElements_returnsFalse() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());

        // when
        list.add(createArrayListWith3Elements().get(0));
        list.add(createArrayListWith3Elements().get(1));

        // then
        test.assertFalse(list.containsAll(arrayList), "containsAll() returns true when not all of elements are present in the list");
    }

    @Test
    private static void containsAll_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.containsAll(new ArrayList<>());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for containsAll() call on null object");
        }
    }

    @Test
    private static void addAll_adsListWith_3_elementsToList_listSizeIs_3() {
        // given

        // when
        list.addAll(createArrayListWith3Elements());

        // then
        test.assertTrue(list.size() == 3, "After addAll() of 3 elements for empty list, list size is different than 3");
    }

    @Test
    private static void addAll_adsElementsToNonEmptyList_newElementsAreAddedAtEndOfList() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.addAll(createListWith2Elements());

        // then
        test.assertTrue(list.indexOf(createListWith2Elements().get(0)) == 3, "addAll() is not adding elements from the end of the list ");
    }

    @Test
    private static void addAll_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.addAll(new ArrayList<>());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for addAll() call on null object");
        }
    }

    @Test
    private static void addAllWithIndexSpecified_adsElementsToNonEmptyListAtSpecifiedIndex_newElementsAreAddedStartingFromSpecifiedIndex() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.addAll(1, createListWith2Elements());

        // then
        test.assertTrue(list.indexOf(createListWith2Elements().get(0)) == 1, "addAll() withIndexSpecified is adding elements add incorrect position");
    }

    @Test
    private static void addAllWithIndexSpecified_adsElementsToNonEmptyListAtSpecifiedIndex_shiftsElementsWhichAreAfterSpecifiedIndex() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.addAll(1, createListWith2Elements());

        // then
        test.assertTrue(list.indexOf(createArrayListWith3Elements().get(1)) == 3, "addAll() withIndexSpecified is not shifting elements which are after specified index correctly");
    }

    @Test
    private static void addAllWithIndexSpecified_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.addAll(1, new ArrayList<>());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for addAll() withIndexSpecified call on null object");
        }
    }

    @Test
    private static void removeAll_listContainsSpecifiedElements_elementsAreRemovedFromList() {
        // given

        list.addAll(createArrayListWith3Elements());
        list.add(createElement());

        // when
        list.removeAll(createArrayListWith3Elements());

        // then
        test.assertFalse(list.containsAll(createArrayListWith3Elements()), "Elements are still present in the list after removeAll() call for them");
    }

    @Test
    private static void removeAll_listDoesNotContainElements_listRemainsUnchanged() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createListWith2Elements());

        list.addAll(createArrayListWith3Elements());

        ArrayList<String> arrayListWithElementsOfList = new ArrayList<>();
        arrayListWithElementsOfList.addAll(list);

        // when
        arrayList.removeAll(arrayList);

        // then
        test.assertTrue(list.containsAll(arrayListWithElementsOfList),"removeAll() for elements which are not present in the list" +
                " modified the list");
    }

    @Test
    private static void removeAll_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.removeAll(new ArrayList<>());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for removeAll() call on null object");
        }
    }

    @Test
    private static void retainAll_bothListsContainsSameElements_listHasNotBeenChanged() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createListWith2Elements());
        list.addAll(createListWith2Elements());

        // when
        list.retainAll(arrayList);

        // then
        test.assertTrue(list.containsAll(arrayList), "retainAll() removes elements for lists with the same elements");
    }

    @Test
    private static void retainAll_oneElementIsDifferentInLists_differentElementHasBeenRemoved() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createListWith2Elements());

        list.addAll(createListWith2Elements());
        list.add(createDifferentElement());

        // when
        list.retainAll(arrayList);

        // then
        test.assertFalse(list.contains(createDifferentElement()), "retainAll() does not remove element which was present in one list" +
                " but not in the other");
    }

    @Test
    private static void retainAll_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.retainAll(new ArrayList<>());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for retainAll() call on null object");
        }
    }

    @Test
    private static void replaceAll_emptyList_doesNotChangeAnything() {
        // given

        // when
        list.replaceAll(e -> e + "a");
        // then
        test.assertTrue(list.size() == 0, "replaceAll() has added elements to the list");
    }

    @Test
    private static void replaceAll_changesEveryElementAccordingToOperation() {
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(Arrays.asList(createArrayListWith3Elements().get(0) + "a",
                                       createArrayListWith3Elements().get(1) + "a",
                                       createArrayListWith3Elements().get(2) + "a"));

        list.addAll(createArrayListWith3Elements());

        // when
        list.replaceAll(e -> e + "a");

        // then
        test.assertTrue(list.containsAll(arrayList), "replaceAll() does not changed elements according to the operation");
    }

    @Test
    private static void replaceAll_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.replaceAll(e -> e + "a");
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for replaceAll() call on null object");
        }
    }

    @Test
    private static void sort_unsortedList_returntSortedList() {
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.sort(String.CASE_INSENSITIVE_ORDER);

        // then
        test.assertTrue  ((list.indexOf("Academy") == 0) &&
                        (list.indexOf("Course") == 1) &&
                        (list.indexOf("Java") == 2),
                        "sort() does not sorted list");
    }

    @Test
    private static void sort_sortedArray_indexesOfElementsHaveNotChanged(){
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.sort(String.CASE_INSENSITIVE_ORDER);

        // then
        test.assertTrue  ((list.indexOf("Academy") == 0) &&
                (list.indexOf("Course") == 1) &&
                (list.indexOf("Java") == 2),
                "sort() changed indexes of sorted list");
    }

    @Test
    private static void sort_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.sort(String.CASE_INSENSITIVE_ORDER);
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for sort() call on null object");
        }
    }

    @Test
    private static void clear_forListWithElements_removesElementsFromList(){
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        list.clear();

        // then
        test.assertTrue(list.size() == 0, "clear() does not cleared the list");
    }

    @Test
    private static void clear_forListWithoutElements_doesNotChangeList(){
        // given

        // when
        list.clear();
        // then
        test.assertTrue(list.size() == 0, "clear() added elements to the empty list");
    }

    @Test
    private static void clear_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.clear();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for clear() call on null object");
        }
    }

    @Test
    private static void equals_forTheSameLists_returnsTrue(){
        // given
        List newList = list;

        // when
        
        // then
        test.assertTrue(list.equals(newList), "equals() returns false for the same list");
    }

    @Test
    private static void equals_forDifferentLists_returnsFalse(){
        // given
        ArrayList<String> newList = new ArrayList<>();
        newList.add(createElement());
        // when

        // then
        test.assertFalse(list.equals(newList), "equals() for different lists returns true");
    }

    @Test
    private static void equals_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.equals(new ArrayList());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for equals() call on null object");
        }
    }

    @Test
    private static void hashCode_forSameList_isEqual(){
        // given
        List newList = list;
        // when

        // then
        test.assertTrue(list.hashCode() == newList.hashCode(), "hashCode() returns false for the same list");
    }

    @Test
    private static void hashCode_forEmpyList_returns_1(){
        // given

        // when

        // then
        test.assertTrue(list.hashCode() == 1, "hashCode() returns value different than 1 for empty list");
    }

    @Test
    private static void hashCode_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.hashCode();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for hashCode() call on null object");
        }
    }

    @Test
    private static void get_forEmptyList_throwsIndexOutOfBoundException(){
        // given

        // when
        try{
            list.get(0);
        } catch (Exception e){
            // then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class), "Exception different than index out of bound exception" +
                    " has been thrown for get() call on empty list");
        }
    }

    @Test
    private static void get_listWithElementOnIndex_0_calledWith_0_returnsElement(){
        // given
        list.add(createElement());

        // when

        // then
        test.assertTrue(list.get(0).equals(createElement()), "get() call for element on index 0 returns different element");
    }

    @Test
    private static void get_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.get(0);
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for get() call on null object");
        }
    }

    @Test
    private static void set_emptyListAdElementAtIndex_0_throwsIndexOutOfBoundException(){
        // given

        // when
        try{
            list.set(0, createElement());
        } catch (Exception e){
            // then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class), "Exception different than index out of bound exception" +
                    " has been thrown for set() call on empty list");
        }
    }

    @Test
    private static void set_listWithElementAtPosition_0_setForNewElementAtPosition_0_elementIsSetAtPosition_0(){
        // given

        // when
        list.add(createElement());
        list.set(0, createDifferentElement());

        // then
        test.assertTrue(list.get(0).equals(createDifferentElement()), "set() does not put element at desired position");
    }

    @Test
    private static void set_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.set(0, createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for set() call on null object");
        }
    }

    @Test
    private static void addWithIndex_emptyListAddAt_0_index_throwsIndexOutOfBoundException(){
        // given

        // when
        try{
            list.add(0, createElement());
        } catch (Exception e){
            //then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class), "add() with index does throw exception" +
                    " different than IndexOutOfBoundsException in case of add on empty list");
        }
    }

    @Test
    private static void addWithIndex_listWithOneElement_addElementAt_0_position_newElementIsAt_0_position_previousElementIsShifted(){
        // given

        // when
        list.add(createElement());
        list.add(0, createDifferentElement());

        // then
        test.assertTrue ((list.get(0) == createDifferentElement()) && (list.get(1) == createElement()), "add() with index specified does not put element at " +
                "specified position or does not shift elements correctly");
    }

    @Test
    private static void addWithIndex_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.add(0, createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for add() with index call on null object");
        }
    }

    @Test
    private static void remove_withIndex_emptyList_throwsIndexOutOfBoundException(){
        // given

        // when
        try{
            list.remove(0);
        } catch (Exception e){
            // then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class), "Exception different than IndexOutOfBoundsException" +
                    " has been thrown for remove() call on empty list");
        }
    }

    @Test
    private static void remove_withIndexRemoveCallForListWithOneElement_sizeOfListIs_0(){
        // given
        list.add(createElement());

        // when
        list.remove(0);

        // then
        test.assertTrue(list.size() == 0, "Size is different than 0 for remove() call on 1 elements' list");
    }

    @Test
    private static void removeWithIndex_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.remove(0);
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for remove() with index call on null object");
        }
    }

    @Test
    private static void indexOf_emptyList_throwsIndexOutBoundException(){
        // given

        // when
        try {
            list.indexOf(createElement());
        } catch (Exception e){
            // then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class), "Exception different than IndexOutOfBoundsException" +
                    " has been thrown for indexOf() call on empty list");
        }
    }

    @Test
    private static void indexOf_listContainsElement_indexOfElementIsReturned(){
        // given
        list.add(createElement());

        // when

        // then
        test.assertTrue(list.indexOf(createElement()) == 0,"Index different than element's index has been returned for indexOf()");
    }

    @Test
    private static void indexOf_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.indexOf(createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for indexOf() with index call on null object");
        }
    }

    @Test
    private static void lastIndexOf_twoSameElementsInList_returnsIndexOfLastOne(){
        // given

        // when
        list.addAll(Arrays.asList(createElement(), createElement()));

        // then
        test.assertTrue(list.lastIndexOf(createElement()) == 1, "Index different than 1 returned by lastIndexOf() for list which is containing last requested" +
                " element at position 1");
    }

    @Test
    private static void lastIndexOf_noElementInList_returns_negative1(){
        // given

        // when
        list.addAll(Arrays.asList(createElement(), createElement()));

        // then
        test.assertTrue(list.indexOf(createDifferentElement()) == -1, "Value different than -1 returned by lastIndexOf() for list which does not contains " +
                "specyfic element");
    }

    @Test
    private static void lastIndexOf_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.lastIndexOf(createElement());
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for lastIndexOf() with index call on null object");
        }
    }

    @Test
    private static void listIterator_emptyList_doesNotHaveElements(){
        // given

        // when
        ListIterator listIterator = list.listIterator();

        // then
        test.assertFalse(listIterator.hasNext(),"listIterator() returns non empty iterator for empty list");
    }

    @Test
    private static void listIterator_listContainsObjects_iterationOverIteratorReturnsObjectsInSameOrder(){
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());
        boolean result = true;

        // when
        list.addAll(arrayList);
        Iterator listIterator = list.listIterator();

        // then
        int i = 0;
        while (listIterator.hasNext()){
            if(!listIterator.next().equals(arrayList.get(i))){
                result = false;
            }
            i++;
        }

        test.assertTrue( result, "listIterator() returns elements in different order than they are stored in list");
    }

    @Test
    private static void listIterator_forReferenceToNull_throwsNullPointerException() {
        // given
        list = null;

        // when
        try {
            list.listIterator();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for iterator() with index call on null object");
        }
    }

    @Test
    private static void listIterator_withIndex_indexOutOfBound_throwsIndexOutOfBoundException(){
        // given
        list.add(createElement());

        // when
        try{
            list.listIterator(1);
        } catch (Exception e){
            // then
            test.assertTrue(e.getClass().equals(IndexOutOfBoundsException.class),"Exception different than IndexOutOfBoundException " +
                    "has been thrown for listIterator(index) when index is out of bound");
        }
    }

    @Test
    private static void listIterator_withIndex_elementsInList_elementsFromSpecyficIndexAreReturned(){
        // given
        boolean result = true;
        int index = 1;
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());

        // when
        list.addAll(arrayList);
        Iterator listIterator = list.listIterator(index);

        // then

        while (listIterator.hasNext()){
            if(!listIterator.next().equals(arrayList.get(index))){
                result = false;
            }
            index++;
        }

        // then
        test.assertTrue(result, "Elements different than specified in list has been returned for listIterator() with index specified");
    }

    @Test
    private static void listIterator_withIndex_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try {
            list.listIterator();
        } catch (Exception e) {
            // then
            test.assertTrue(e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for iterator() with index call on null object");
        }
    }

    @Test
    private static void sublist_startEndIndexTheSame_emptyListReturned(){
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());

        // when
        list.addAll(arrayList);
        List newList = list.subList(0, 0);

        // then
        test.assertTrue(newList.size() == 0, "sublist() returns non empty list for same start and end index as arguments");
    }

    @Test
    private static void sublist_startEndIndexDifferenceOfOneInStartEndIndexes_returnsOneElement(){
        // given
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.addAll(createArrayListWith3Elements());

        // when
        list.addAll(arrayList);
        List newList = list.subList(0, 1);

        // then
        test.assertTrue( newList.size() == 1, "sublist() returns different amount of elements than 1 for start/end index " +
                " in difference of 1");
    }

    @Test
    private static void sublist_withIndex_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try {
            list.subList(0, 1);
        } catch (Exception e) {
            // then
            test.assertTrue( e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for subList() call on null object");
        }
    }

    @Test
    private static void spliterator_listWith_4_elements_returnsIteratorSize_4(){
        // given
        list.addAll(createArrayListWith3Elements());

        // when
        Spliterator<String> spliterator = list.spliterator();

        // then
        test.assertTrue(spliterator.getExactSizeIfKnown() == list.size(), "spliterator() returns object with different size " +
                "than object for which it was called");
    }

    @Test
    private static void spliterator_emptyList_returnsIteratorSize_0(){
        // given

        // when
        Spliterator<String> spliterator = list.spliterator();
        // then
        test.assertTrue( spliterator.getExactSizeIfKnown() == 0, "spliterator() for empty list returns object with size different " +
                "than 0");
    }

    @Test
    private static void spliterator_withIndex_forReferenceToNull_throwsNullPointerException(){
        // given
        list = null;

        // when
        try {
            list.spliterator();
        } catch (Exception e) {
            // then
            test.assertTrue( e.getClass().equals(NullPointerException.class), "Exception different than null pointer exception" +
                    " has been thrown for subList() call on null object");
        }
    }


    @Test
    private static void testingTestEnvironmentFalse() {
        // given

        // when

        // then
        test.assertTrue(false, "testing test environment");

    }
}