/**
 * ClassLocationDemo.java
 *
 * Demonstrates how to:
 * 1. Obtain a class's fully-qualified name.
 * 2. Convert it to a resource path.
 * 3. Locate the .class file via Class.getResource(...) 
 *    and via the context ClassLoader.
 *
 * To compile:
 *   javac ClassLocationDemo.java
 * To run:
 *   java ClassLocationDemo
 *
 * No external dependencies - just the JDK.
 */
package example;

public class ClassLocationDemo {

    /**
     * Prints out:
     * - The fully-qualified class name.
     * - The corresponding resource path.
     * - The URL at which the .class file was loaded,
     *   found via both Class.getResource(...) and
     *   ClassLoader.getResource(...).
     *
     * @param args not used
     * @throws Exception if any resource lookup fails
     */
    public static void main(String[] args) {
        try {
            // 1. Fully-qualified class name
            String fqcn = ClassLocationDemo.class.getName();
            System.out.println("Fully-qualified class name: " + fqcn);

            // 2. Convert to resource path (dots -> slashes, add .class)
            String resourcePath = "/" + fqcn.replace('.', '/') + ".class";
            System.out.println("Resource path: " + resourcePath);

            // 3A. Locate via Class.getResource (absolute lookup)
            java.net.URL urlViaClass = ClassLocationDemo.class.getResource(resourcePath);
            System.out.println("URL via Class.getResource: " + urlViaClass);
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            // System.err.println("Error: " + ex);
            // System.err.println("Exception type: " + ex.getClass().getName());
            // System.err.print("Exception args: ");
            // if (ex.getMessage() != null) {
            //     System.err.println(ex.getMessage());
            // } else {
            //     System.err.println("null");
            // }
        }
        // // 3B. Locate via context ClassLoader
        // ClassLoader loader = Thread.currentThread().getContextClassLoader();
        // java.net.URL urlViaLoader = loader.getResource(resourcePath);
        // System.out.println("URL via ClassLoader.getResource: " + urlViaLoader);

        // // 4. Bonus: verify that both URLs are equal
        // System.out.println("Both URLs equal? " + urlViaClass.equals(urlViaLoader));
    }
}
