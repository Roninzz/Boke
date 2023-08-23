/**
 * 分类
 */
export interface Category {
    /**
     * 分类id
     */
    id: number;
    /**
     * 分类名
     */
    categoryName: string;
    /**
     * 文章数量
     */
    articleCount: number;
}

export interface CategoryVO {
    /**
     * 分类id
     */
    id: number;
    /**
     * 分类名
     */
    categoryName: string;
}