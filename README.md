# SetWallpaper

This is an isolated test i did for an application i am developing and i found some interesting concepts to explain, 
so this little app will act as a visual help for the explanation.

Concepts i want to explain: 

- Show default data in ImageViews
- Do not block the UI thread because of code execution
- Show to the user if something is being loaded or someting went wrong

Libraries i used

- Glide
- Coroutines 

What does the app do? 

It is a single Activity application that takes the path of an image from your device, prints it on a View
and then, you have the option to make that image your device's wallpaper.

SHOW DEFAULT DATA

![AppScreenchot](https://user-images.githubusercontent.com/54866393/98014150-8d53ac80-1dfb-11eb-8b84-5826ab6bcb83.png)

In this case, i am expecting for the user to select an image, but before he selects it, i have nothing to paint. A good
solution for this is to print a default image. In this case i used a color, not an image, but this is good because:

- It helps you locate where the image will be, size, how it looks while you are coding
- It shows the user where the image will be when he selects it


DO NOT BLOCK UI


![SelectImage](https://user-images.githubusercontent.com/54866393/98015360-fab40d00-1dfc-11eb-8c6e-b8c418971a88.gif)

I realised that i was delaying the transaction from the gallery intent to my MainActivity because i was
executing a lot of code that was called in the onActivityResult. The solution for this issue as to 
execute the code in a coroutine, so comming back to my MainActivity was not depending on my printImageWithGlide
method. 

As its shwown in the GIF, the image appears after i come back to the activity.





