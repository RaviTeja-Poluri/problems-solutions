import java.util.*;

public class BstOperations {

    static BinaryTree binaryTree;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        int totalQueries = scanner.nextInt();
        scanner.nextLine();
        while (totalQueries != 0) {
            String query = scanner.nextLine();
            String[] splittedQuery = query.split(" ");
            String operation = splittedQuery[0];
            String value = splittedQuery[1];
            if ("i".equals(operation)) {
                System.out.println(addValue(value));
            }
            if ("d".equals(operation)) {
                System.out.println(deleteValue(value));
            }
            totalQueries--;
        }
    }


    private static int addValue(String value) {
        int index = 0;
        if (binaryTree == null) {
            binaryTree = new BinaryTree();
            index = 1;
            binaryTree.setIndex(index);
            binaryTree.setValue(value);
        } else {
            BinaryTree emptyRootLocation = getBranchWithEmptyLeaf(binaryTree);
            int rootIndex = emptyRootLocation.getIndex();
            BinaryTree right = emptyRootLocation.getRight();
            if (right != null) {
                BinaryTree leftTree = new BinaryTree();
                leftTree.setValue(value);
                index = 2 * rootIndex;
                leftTree.setIndex(index);
                emptyRootLocation.setLeft(leftTree);
            } else {
                BinaryTree leftTree = new BinaryTree();
                leftTree.setValue(value);
                index = (2 * rootIndex) + 1;
                leftTree.setIndex(index);
                emptyRootLocation.setRight(leftTree);
            }
        }
        return index;
    }

    private static BinaryTree getBranchWithEmptyLeaf(BinaryTree binaryTree) {
        if (binaryTree.hasEmptyLeaf()) return binaryTree;
        BinaryTree left = binaryTree.getLeft();
        BinaryTree right = binaryTree.getRight();
        if (right.hasEmptyLeaf()) {
            return right;
        } else  {
            return left;
        }
    }


    private static int deleteValue(String value) {
        int index = 0;
        Queue<BinaryTree> q = new LinkedList<>();
        if (binaryTree.getValue().equals(value)) {
            index = binaryTree.getIndex();
            binaryTree = null;
            return index;
        }
        BinaryTree queueValue;
        q.add(binaryTree);
        while (q.size() > 0) {
            queueValue = q.peek();
            if (queueValue.getValue().equals(value)) {
                index = queueValue.getIndex();
                break;
            }
            q.remove();
            if (queueValue.getRight() != null) {
                if (queueValue.getRight().getValue().equals(value)) {
                    index = queueValue.getRight().getIndex();
                    queueValue.setRight(null);
                    break;
                }
                q.add(queueValue.getRight());
            }

            if (queueValue.getLeft() != null) {
                if (queueValue.getLeft().getValue().equals(value)) {
                    index = queueValue.getLeft().getIndex();
                    queueValue.setLeft(null);
                    break;
                }
                q.add(queueValue.getLeft());
            }
        }
        return index;
    }
}


class BinaryTree {
    private int index;
    private String value;
    private BinaryTree left;
    private BinaryTree right;

    public boolean hasEmptyLeaf() {
        return left == null || right == null;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    @Override
    public String toString() {
        return "BinaryTree{" +
                "index=" + index +
                ", value='" + value + '\'' +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
