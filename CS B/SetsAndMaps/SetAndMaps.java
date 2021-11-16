import java.io.*;
import java.util.*;
//COMPLETE THE METHODS BELOW:  extra credit for using iterators

/* A Set is simply a Collection in which duplicates are not allowed. 
In a Map, each element consists of a unique key and a value.
In a TreeMap, the elements are organized in a balanced and ordered tree, ordered by the keys. 
In a HashMap, the elements are organized in a hash table determined by the Object's hashCode(). 
A HashSet can be thought of as a HashMap in which each element has the same dummy value.
A TreeSet can be thought of as a TreeMap in which each element has the same dummy value.

interface java.util.Set
-> boolean add(Object x);			//boolean for successful add
-> boolean contains (Object x);	//search for x in the Set
-> boolean remove (Object x);		//boolean for successful remove
-> int size();							//the number of elements
-> Iterator iterator();				//to traverse the set
-> Object[] toArray();				//copies Set objects into an array which is returned

class java.util.HashSet implements java.util.Set
//places Objects into a set by their hashCode() method
class java.util.TreeSet implements java.util.Set
//places Objects into a balanced search tree

interface java.util.Map
-> Object put(Object key, Object value);	//adds unique key maped to value
-> Object get(object key);						//returns value associated with key
-> Object remove (Object key);				//returns value removed
-> int size();										//the number of elements
-> Set keySet();									//returns a set of all the keys
-> boolean containsKey(Object key);			//looks to see if key exists in the keyset									

class java.util.HashMap implements java.util.Map
//places Objects into a map by their hashCode() method
class java.util.TreeMap implements java.util.Map
//places Objects into a balanced search tree
*/

public class SetAndMaps
{
//post:  this will return a HashSet of all the elements from s1 and s2
   public static Set union(Set s1, Set s2)  {
      Set ans = new HashSet();	
      Iterator itr = s1.iterator(); 

      while (itr.hasNext()) {
         ans.add(itr.next());
      }
      
      itr = s2.iterator();

      while (itr.hasNext()) {
         ans.add(itr.next());
      }

      return ans;
   }

//post: this will return only those elements common to both s1 and s2
   public static Set intersection(Set s1, Set s2) {
      Set ans = new HashSet();
      Iterator itr = s1.iterator();

      while (itr.hasNext()) {
         Object obj = itr.next();
         if (s2.contains(obj)) {
            ans.add(obj);
         }
      }

      return ans;
   }

//post: this will return the elements of s1 that are not in s2
   public static Set difference(Set s1, Set s2) {
      Set ans = new HashSet();
      Iterator itr = s1.iterator();

      while (itr.hasNext()) {
         Object obj = itr.next();
         if (!s2.contains(obj)) {
            ans.add(obj);
         }
      }

      return ans;
   }

//post:  this will return only those elements that are unique to s1 and unique to s2
//			also known as the opposite of intersection (think union - intersection)
   public static Set exclusion(Set s1, Set s2) {
      Set ans = new HashSet();
      Iterator itr = s1.iterator();

      while (itr.hasNext()) {
         Object obj = itr.next();
         if (!s2.contains(obj)) {
            ans.add(obj);
         }
      }

      itr = s2.iterator();

      while (itr.hasNext()) {
         Object obj = itr.next();
         if (!s1.contains(obj)) {
            ans.add(obj);
         }
      }

      return ans;
   }

//post:  will return a map whose keySet is comprised of all the values in m
//			and whose values are arrays of all the keys from m that mapped to that value
//i.e. if m is a treeMap of students (keys) and grades (values), then flip returns
//a TreeMap of grades (keys) and all the students who made that grade in an ArrayList (value)

   public static Map flip(Map<String, String> m) {
      Map<String, ArrayList<String>> ans = new TreeMap();
      for (Map.Entry<String, String> e : m.entrySet()) {
         String key = (String)e.getKey();
         String val = (String)e.getValue();

         if (!ans.containsKey(val)) {
            ArrayList<String> list = new ArrayList<String>();
            list.add(key);
            ans.put(val, list);
         } else {
            ArrayList<String> list = (ArrayList<String>)ans.get(val);
            list.add(key);
            ans.put(val, list);
         }
      }
      
      return ans;
   }

   public static void main(String[] arg)
   
   {
      Set<String> keyOfG = new TreeSet();
      keyOfG.add("G");
      keyOfG.add("Am");
      keyOfG.add("Bm");
      keyOfG.add("C");
      keyOfG.add("D");
      keyOfG.add("Em");
      keyOfG.add("F#m7dim5");
   
      Set<String> keyOfC = new TreeSet();
      keyOfC.add("C");
      keyOfC.add("Dm");
      keyOfC.add("Em");
      keyOfC.add("F");
      keyOfC.add("G");
      keyOfC.add("Am");
      keyOfC.add("Bm7dim5");
   
      System.out.println("Chords in the key of G:");
      System.out.println(keyOfG+"\n");
   //[Am, Bm, C, D, Em, F#m7dim5, G]
   
      System.out.println("Chords in the key of C:");
      System.out.println(keyOfC+"\n");
   //[Am, Bm7dim5, C, Dm, Em, F, G]
   
      Set theirU = union(keyOfG, keyOfC);
      System.out.println("The Union of chords between the keys of G and C:");
      System.out.println(theirU+"\n");
   //[C, D, F#m7dim5, F, G, Em, Bm7dim5, Dm, Bm, Am]
   
      Set theirI = intersection(keyOfG, keyOfC);
      System.out.println("The Intersection of chords between the keys of G and C:");
      System.out.println(theirI+"\n");
   //[C, G, Em, Am]
   
      Set theirD = difference(keyOfG, keyOfC);
      System.out.println("The Difference in chords between the keys of G and C:");
      System.out.println(theirD+"\n");
   //[D, F#m7dim5, Bm]
   
      theirD = difference(keyOfC, keyOfG);
      System.out.println("The Difference in chords between the keys of C and G:");
      System.out.println(theirD+"\n");
   //[F, Bm7dim5, Dm]
   
      Set theirE = exclusion(keyOfG, keyOfC);
      System.out.println("The Exclusion of chords between the keys of G and C:");
      System.out.println(theirE+"\n");
   //[D, F#m7dim5, F, Bm7dim5, Dm, Bm]
   
      Map<String, String> tm = new TreeMap();
      tm.put("Ginger", "C");
      tm.put("Scary", "A");
      tm.put("Posh", "B");
      tm.put("Sporty", "B");
      tm.put("Baby", "C");
      tm.put("MaryAnne", "B");
      tm.put("Samantha", "A");
      System.out.println("TreeMap of students (key) with their respective grades (value):");
      System.out.println(tm+"\n");
   //{Baby=C, Ginger=C, MaryAnne=B, Posh=B, Samantha=A, Scary=A, Sporty=B}
   
      Map<String, ArrayList<String>> byGrade = flip(tm);
      System.out.println("TreeMap of grades (key) with all the students who made that grade (value):");
      System.out.println(byGrade+"\n");
   //{A=[Samantha, Scary], B=[MaryAnne, Posh, Sporty], C=[Baby, Ginger]}
   }

}