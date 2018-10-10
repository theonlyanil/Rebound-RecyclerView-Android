Rebound-RecyclerView-Android[![](https://jitpack.io/v/theonlyanil/Rebound-RecyclerView-Android.svg)](https://jitpack.io/#theonlyanil/Rebound-RecyclerView-Android)

===

Unofficial library based on Facebook's Rebound library for Android RecyclerView. Report issues in issues tab. Special Thanks to [@anthony-skr](https://github.com/anthony-skr/RecyclerViewReboundEntrance). [Read his article](http://anthony-skr.com/article/recyclerview-items-animation-with-rebound-effect)

Implementation
===
build.gradle(Project)
```gradle
  allprojects {
    repositories {
        maven { url 'https://jitpack.io' }
    }
}
```

build.gradle (Module:app)
```gradle
	dependencies {
	        implementation 'com.github.theonlyanil:Rebound-RecyclerView-Android:0.1'
	}
```

Usage
===

Initialize in OnCreate

```java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerMain);

        ReboundRecycler.init(recyclerView);
```

Go to Recycler Adapter and call ReboundRecycler in ```java onCreateViewHolder``` and ```java onBindViewHolder```

```java
      @Override
        public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            RelativeLayout relativeLayout = (RelativeLayout) LayoutInflater.from(context).inflate(R.layout.single_main, viewGroup, false);
            ReboundRecycler.first(relativeLayout);
            return new ViewHolder(relativeLayout);
        }
```

```java
      @Override
        public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder viewHolder, int i) {
            ReboundRecycler.bind(viewHolder.itemView, i);
        }
```        

Advanced Usage (Init)
===
You can also modify these values: Delay (animation start delay), Init Tension, Init Friction, Scroll Tension, Scroll Friction.
[Play with tension and friction](http://facebook.github.io/rebound/).

```java
      ReboundRecycler.init(recyclerView)
          .delay(1000)                // loads after 1 second
          .initFriction(30)
          .initTension(250)
          .scrollFriction(25)
          .scrollTension(300);
```
