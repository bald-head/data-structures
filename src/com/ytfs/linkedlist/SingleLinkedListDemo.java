package com.ytfs.linkedlist;

/**
 * @author by ytfs
 * @Description 单向链表
 * @Create 2020/9/11-23:34
 */
public class SingleLinkedListDemo {
    public static void main(String[] args) {

        //先创建节点
        HeroNode hero1 = new HeroNode(1, "宋江", "及时雨");
        HeroNode hero2 = new HeroNode(2, "卢俊义", "玉麒麟");
        HeroNode hero3 = new HeroNode(3, "吴用", "智多星");
        HeroNode hero4 = new HeroNode(4, "林冲", "豹子头");

        //创建一个链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();
//        //添加节点
//        singleLinkedList.add(hero1);
//        singleLinkedList.add(hero2);
//        singleLinkedList.add(hero3);
//        singleLinkedList.add(hero4);


        //添加节点，考虑编号顺序添加
        singleLinkedList.addByOrder(hero1);
        singleLinkedList.addByOrder(hero4);
        singleLinkedList.addByOrder(hero3);
        singleLinkedList.addByOrder(hero2);

        //删除前输出链表信息
        singleLinkedList.list();

//        HeroNode newHeroNode = new HeroNode(1, "小宋", "及时雨~~~");
//        singleLinkedList.update(newHeroNode);


        singleLinkedList.del(3);

        //输出链表信息

        singleLinkedList.list();

    }
}


/**
 * @Description 定义单链表
 */
class SingleLinkedList {

    //初始化一个头结点,里面什么都不放
    HeroNode head = new HeroNode(0, "", "");

    /**
     * 向链表中添加一个节点
     * 不考虑编号顺序
     */
    public void add(HeroNode heroNode) {
        //因为头节点是不能动的，使用中间变量temp来保存头节点
        HeroNode temp = head;
        //遍历节点，找到最后一个节点【通过最后一个节点next指向为 null】
        while (true) {
            if (temp.next == null) {
                //找到最后一个节点
                break;
            }
            //没有找到就将temp后移一个
            temp = temp.next;
        }
        // 当循环退出的时候temp就指向节点的最后一个
        temp.next = heroNode;
    }

    /**
     * 考虑排序编号添加
     *
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode) {
        //因为头节点是不能动的,使用辅助变量temp寻找位置
        HeroNode temp = head;

        //用来代表当前添加的节点的编号是否存在，默认false不存在
        boolean flag = false;

        while (true) {
            if (temp.next == null) {
                //当前temp为链表最后一个
                break;
            }
            if (temp.next.no > heroNode.no) {
                //找到需要添加的位置，就在temp后面添加
                break;
            } else if (temp.next.no == heroNode.no) {
                //需要添加的节点已经存在
                flag = true;
                break;
            }
            //三个条件不成立  temp后移
            temp = temp.next;
        }
        if (flag) {
            //不能添加，编号存在
            System.out.printf("当前添加的英雄编号 %d 已经存在， 不能添加\n", heroNode.no);
        } else {
            //在temp后面进行添加
            heroNode.next = temp.next;
            temp.next = heroNode;
        }
    }

    /**
     * 更新节点
     *
     * @param newHeroNode
     */
    public void update(HeroNode newHeroNode) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        //因为头节点是不能动的，使用中间变量temp来保存头节点
        HeroNode temp = head;
        boolean flag = false;   //用来表示链表中是否存在需要修改的节点
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.no == newHeroNode.no) {
                flag = true;
                break;
            }
            temp = temp.next;   //temp后移
        }
        if (flag) {
            temp.name = newHeroNode.name;
            temp.nickName = newHeroNode.nickName;
        } else {
            System.out.printf("没有找到编号为：%d 节点", newHeroNode.no);
        }
    }

    /**
     * 删除节点
     *
     * @param no
     */
    public void del(int no) {
        if (head.next == null) {
            System.out.println("当前链表为空");
            return;
        }
        //因为头节点是不能动的，使用中间变量temp来保存头节点
        HeroNode temp = head;

        boolean flag = false;   //表示是否找到需要删除的节点

        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next;
        }
        //删除需要删除的节点
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.printf("不存在编号为 %d 的节点  删除失败\n", no);
        }

    }

    /**
     * 遍历所有的节点
     */
    public void list() {
        //因为头节点是不能动的，使用中间变量temp来保存头节点
        HeroNode temp = head.next;
        while (true) {
            //判断链表是否为空
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            //将节点后移
            temp = temp.next;
        }
    }

}

/**
 * @Description 节点
 */
class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;  //指向下一个域

    /**
     * 构造器
     *
     * @param no
     * @param name
     * @param nickName
     */
    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName +
                '}';
    }


}