

### RoundRectLayout 

一个圆角图片的解决方案，解决设计需要不同圆角图片时，避免重复让设计师出不一样的切图。

![圆角图形.jpg](https://upload-images.jianshu.io/upload_images/1933299-7789175d0cf49689.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240)

---

#### 使用步骤

在xml文件直接引用

```
   <com.android.round.weight.RoundRectLayout
                    android:layout_width="100dp"
                    android:layout_height="100dp"
                    android:layout_margin="30dp"
                    app:round_rect_circle="true">

                    <ImageView
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:scaleType="centerCrop"
                        android:src="@mipmap/club_bg"/>

                    <TextView
                        android:layout_width="match_parent"
                        android:layout_height="40dp"
                        android:layout_alignParentBottom="true"
                        android:background="@mipmap/bottom_bg"
                        android:gravity="center"
                        android:textColor="@android:color/white"
                        android:textSize="10sp"/>

                </com.android.round.weight.RoundRectLayout>

```

##### 属性介绍


参数 | 属性 | 介绍
---|---|---
round_rect_circle | boolean | 是否显示圆形
round_rect_corner | int | 圆角大小
round_rect_corner_top_to_left | int | 左上角圆弧
round_rect_corner_top_to_right | int | 右上角圆弧
round_rect_corner_bottom_to_left | int | 左下角圆弧
round_rect_corner_bottom_to_right | int | 右下角圆弧
round_rect_stroke_color | int | 描边颜色
round_rect_stroke_width | int | 描边大小


- round_rect_circle：默认false，如果是true，直接绘制圆形。
- round_rect_corner：四个角圆弧属性，默认为0。
- round_rect_corner_top_to_left：左上角圆弧，如果设置了该属性，round_rect_corner的左上角设置不起作用。
- round_rect_corner_top_to_right：右上角圆弧，如果设置了该属性，round_rect_corner的右上角设置不起作用。
- round_rect_corner_bottom_to_left：左下角圆弧，如果设置了该属性，round_rect_corner的左下角设置不起作用。
- round_rect_corner_bottom_to_right：右下角圆弧，如果设置了该属性，round_rect_corner的右下角设置不起作用。
- round_rect_stroke_color：描边的颜色，默认是白色。
- round_rect_stroke_width：圆弧的描边，默认描边的宽度是0。



---


#### 添加到项目

步骤1：将JitPack存储库添加到构建文件中
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
```

步骤2：添加依赖项
```
	dependencies {
	        implementation 'com.github.1054353861:RoundRectLayout:v1.0'
	}
```


---
##### PS：有问题可以提lssues或者加v1054353861，共同学习，提高！

