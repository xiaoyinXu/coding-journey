> A jar包依赖C jar包的1.0版本，B jar包依赖C jar包的2.0版本, 且C-2.0和C-1.0不兼容。
> 这种情况，我们用maven-shade-plugin插件的relocation来解决。