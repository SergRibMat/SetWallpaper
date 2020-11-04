# SetWallpaper

This is an isolated test I did for an application I am developing and I found some interesting concepts to explain, 
so this little app will act as a visual help for my explanation.

Concepts I want to explain: 

- Show default data in ImageViews
- Do not block the UI thread because of code execution
- If something is being loaded or someting went wrong, comunicate it to the user

Libraries

- Glide
- Coroutines 

Permissions

- SET_WALLPAPER

What does the app do? 

It is a single Activity application that takes the path of an image from your device, prints it on a View
and then, you have the option to make that image your device's wallpaper.

SHOW DEFAULT DATA

![AppScreenchot](https://user-images.githubusercontent.com/54866393/98014150-8d53ac80-1dfb-11eb-8b84-5826ab6bcb83.png)

In this case, I am expecting for the user to select an image, but before it is selected, I have nothing to paint. A good
solution for this is to print a default image. For this particular case I used a color, not an image, but this is good because:

- It helps you locate where the image will be, size, how it looks while you are coding
- It shows the user where the image will be when he selects it


DO NOT BLOCK UI


![SelectImage](https://user-images.githubusercontent.com/54866393/98015360-fab40d00-1dfc-11eb-8c6e-b8c418971a88.gif)

I realised that i was delaying the transaction from the gallery intent to my MainActivity because I was
executing a lot of code that was called in the onActivityResult. The solution for this issue was to 
execute the code in a coroutine, so comming back to my MainActivity was not depending on my printImageWithGlide
method. 

As it is shown in the GIF, the image appears after I come back to the activity.

SOMETHING IS BEING LOADED

In case you did not notice, the time between the image is being loaded and the image is already loaded,
a spinner animation is running, so the user knows that the program is working on it.

For finishing, I will give you a GIF to see the Set Wallpaper functionality

![WallpaperChange](https://user-images.githubusercontent.com/54866393/98016724-9f831a00-1dfe-11eb-9789-18543485912d.gif)

The number that you can see at the bottom of the Activity is the resolution of the device. I did not use it here
but it was a good way to test a method that I needed and I decided it was not a bad idea to include it here.

If you arrived to this point, thanks for your time and I hope you found it interesting. :D







