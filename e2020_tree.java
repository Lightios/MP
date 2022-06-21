import java.util.*;

public class e2020_tree
{
    public static void main( String[] args )
    {
        Tree tree = new Tree();
        NodeT root = new NodeT( 5, 3 );

        tree.root = root;

        root.children[ 0 ] = new NodeT( -2, 2 );
        root.children[ 1 ] = new NodeT( -1 ,1 );
        root.children[ 2 ] = new NodeT( -7 ,3 );

        root.children[ 0 ].children[ 0 ] = new NodeT( 7, 1 );
        root.children[ 0 ].children[ 1 ] = new NodeT( 4, 0 );
        root.children[ 1 ].children[ 0 ] = new NodeT( 3, 0 );
        root.children[ 2 ].children[ 0 ] = new NodeT( 4, 2 );
        root.children[ 2 ].children[ 1 ] = new NodeT( 10, 0 );
        root.children[ 2 ].children[ 2 ] = new NodeT( 5, 0 );

        root.children[ 0 ].children[ 0 ].children[ 0 ] = new NodeT( -2, 0 );
        root.children[ 2 ].children[ 0 ].children[ 0 ] = new NodeT( 7, 0 );
        root.children[ 2 ].children[ 0 ].children[ 1 ] = new NodeT( -3, 0 );

        // tree.print( tree.root );
        System.out.println( tree.recMaxVPath( tree.root, 0 ) );
        System.out.println( tree.iterMaxVPath( tree.root ) );
    }
}

class NodeT
{
    public int value;
    public NodeT[] children;

    public NodeT( int v, int childrenCount )
    {
        value = v;

        children = new NodeT[ childrenCount ];
    }
}

class Tree
{
    public NodeT root;

    public void print( NodeT current )
    {
        System.out.println( current.value );
        for ( NodeT child : current.children )
        {
            print( child );
        }
    }


    public int recMaxVPath( NodeT current, int sum )
    {
        sum += current.value;

        for (NodeT child : current.children)
        {
            int childSum = recMaxVPath( child, sum );
            if (childSum > sum)
                return childSum;
        }

        return sum;
    }

    public int iterMaxVPath( NodeT root )
    {
        Stack<NodeT> nodes = new Stack<>();
        Stack<Integer> values = new Stack<>();

        int maxValue = root.value;
        NodeT current = root;
        int currentValue = root.value;

        for (NodeT child : current.children)
        {
            if (currentValue + child.value > maxValue)
            {
                maxValue = currentValue + child.value;
            }

            nodes.push( child );
            values.push( currentValue );
        }

        while (!nodes.isEmpty())
        {
            current = nodes.pop();
            currentValue = values.pop();

            currentValue += current.value;

            for (NodeT child : current.children)
            {
                if (currentValue + child.value > maxValue)
                {
                    maxValue = currentValue + child.value;
                }

                nodes.push( child );
                values.push( currentValue );
            }
        }

        return maxValue;
    }
}

