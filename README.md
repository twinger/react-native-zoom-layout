
# react-native-zoom-layout

## Getting started

`$ npm install react-native-zoom-layout --save`

### Mostly automatic installation

`$ react-native link react-native-zoom-layout`

### Manual installation

#### Android

1. Open up `android/app/src/main/java/[...]/MainActivity.java`
  - Add `import co.twinger.zoomlayout.RNZoomLayoutPackage;` to the imports at the top of the file
  - Add `new RNZoomLayoutPackage()` to the list returned by the `getPackages()` method
2. Append the following lines to `android/settings.gradle`:
  	```
  	include ':react-native-zoom-layout'
  	project(':react-native-zoom-layout').projectDir = new File(rootProject.projectDir, 	'../node_modules/react-native-zoom-layout/android')
  	```
3. Insert the following lines inside the dependencies block in `android/app/build.gradle`:
  	```
      compile project(':react-native-zoom-layout')
  	```

## Usage
```javascript
import ZoomLayout from 'react-native-zoom-layout';

<ZoomLayout
	ref={ref => this.zoomLayout = ref}
	minZoom={1}
	maxZoom={10}
	style={{}}>
	<View style={{
		width: height,
		height: width,
	}}>
		//your view
</ZoomLayout>

```
  