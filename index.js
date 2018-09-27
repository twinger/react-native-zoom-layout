
import React, { Component } from 'react';
import { requireNativeComponent, Platform, ScrollView, UIManager, findNodeHandle } from 'react-native';

const RCTZoomLayout = requireNativeComponent('RCTZoomLayout');

export default class ZoomLayout extends Component {
    state = {}
    static defaultProps = {
        minZoom: 1,
        maxZoom: 3,
    }

    refZoomLayout = ref => {
        this.zoomLayout = ref;
    }

    scrollTo = (x, y, animated) => {
        if (Platform.OS === 'ios') {
            this.zoomLayout.scrollTo(x, y, animated)
        } else {
            UIManager.dispatchViewManagerCommand(
                findNodeHandle(this),
                UIManager.RCTZoomLayout.Commands.moveTo,
                [2, x, y, animated],
            );
        }
    }

    render() {
        const { minZoom, maxZoom, children } = this.props;
        if (Platform.OS === 'ios')
            return (
                <ScrollView
                    {...this.props}
                    contentContainerStyle={this.props.style}
                    ref={this.refZoomLayout}
                    maximumZoomScale={maxZoom}
                    minimumZoomScale={minZoom}
                    pinchGestureEnabled
                    zoomScale={this.state.zoomScale}
                >
                    {children}
                </ScrollView>
            )
        else return (
            <RCTZoomLayout
                {...this.props}
                ref={this.refZoomLayout}
                style={this.props.style}
                minZoom={minZoom}
                maxZoom={maxZoom}
                overPinchable
                zoomEnabled
            >
                {children}
            </RCTZoomLayout>)
    }
}