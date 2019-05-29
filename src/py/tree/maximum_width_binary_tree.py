# Definition for a binary tree node.
# class TreeNode(object):
#     def __init__(self, x):
#         self.val = x
#         self.left = None
#         self.right = None


class Solution(object):

    leftmost_scores = None  # class member to store only leftmost score for every level
    max_width = None  # class member to store max width across all recursion calls

    def widthOfBinaryTree(self, root):
        """
        :type root: TreeNode
        :rtype: int
        """
        self.leftmost_scores = {}
        self.max_width = 0
        self.depth_first_helper(root, 0, 0)
        return self.max_width

    def depth_first_helper(self, root, depth, score):
        """
        :param root:
        :param depth: (int)depth of the node. every recursion calls needs to have this
        :param score: (int) score calculated based on the logic below
        :return:
        The main idea in this question is to give each node a position value.
        If we go down the left neighbor, then position -> position * 2; and if we go down the right neighbor, then position -> position * 2 + 1.
        This makes it so that when we look at the position values L and R of two nodes with the same depth, the width will be R - L + 1.
        """

        if root is None:  # stop condition
            return
        if depth not in self.leftmost_scores.keys():
            self.leftmost_scores[depth] = score
            # for max width, only leftmost node is important, no point in keeping track of other left nodes
            # since we'll be calling in the following line left first this will always hold depth of the left tree

        self.max_width = max(self.max_width, score-self.leftmost_scores.get(depth)+1)
        # However max_width will be computed for evry right node only since if its leftmost node,
        # then current score will be also be same as dictonary score at that level so it will 1 0

        self.depth_first_helper(root.left, depth+1, score*2)
        self.depth_first_helper(root.right, depth+1, score*2+1)