# A type based RecyclerView Adapter [![Build Status](https://travis-ci.com/halcyonmobiledev/recycler-typed-adapter.svg?branch=master)](https://travis-ci.com/halcyonmobiledev/recycler-typed-adapter)

## Scope

Having screens with lists in Android is a very common use case and the need to write a RecyclerView adapter for each of the screens generates a 
lot of boilerplate code. When we talk about lists with multiple item types, this boilerplate code is even more significant, especially, when 
the same item appears in multiple lists and each of the adapters has to handle the given item. A quick solution would be to provide base adapters 
and extend those with only the specific items, however, this is ok in the short run, but it may cause extra overhead in the long run. 
Here, this library comes into the picture.
	
The library itself tries to reduce the amount of boilerplate code by leveraging the handling of an item type to item factories and the role of 
the adapter will be only to find the correct factory which can handle the given item. This way, the adapter becomes independent from the displayed 
items and can be reused in multiple places with different factories setup.

## Setup

**Latest version**: 0.1.0.10

### Make sure that jcenter is added to repositories

```groovy
repositories {
    jcenter()
}
```

### Add the library dependency

Add the following dependency to your build.gradle file.

```groovy
implementation "com.halcyonmobile.recycler-extension:typed-adapter:<latest-version>"
```

## Structure

The library comes with 3 RecyclerView.Adapter with some base implementations, which can be used when there are only simple scenarios,
without any custom solution.

**TypedRecyclerViewAdapter**: 
The base class, represents the backbone of the application, where the whole delegation logic happens.

**PagingTypedRecyclerViewAdapter** 
The logic is inherited from the base class, but extends with handling of paginated lists with a loading more indicator.

**RecyclerAdapter** & **PaginationRecyclerAdapter**
Simple implementations of the previous two abstract classes.

## Usage

You can use any of the adapter implementations based on the requirements for the given list. If the default loading indicator from 
**PaginationRecyclerAdapter** is not suitable for your case, then you can override it and provide your custom loading indicator. 
By default, it uses a circular loading indicator with the primary color.

In order to create and support an item type in a list, you have to follow 4 simple steps:

* Create a data class which holds your domain model and implement the **RecyclerItem** interface.
* Create an item factory (extend the **TypedAdapterBaseFactory** or **ClickableTypedAdapterFactory** base classes) (choose the second if your item needs to be clickable).
* Submit your factory to your adapter instance.
* Map your domain model to the newly created UiModel class and submit that list to the adapter.
* Continue this until you provided a factory for each of the item types which can be present in your list.

**Note** that the order of your submitted factories should take into consideration the occurance of the item. This is important, because 
the underlying implementation searches through the list until it find a factory which can bound the item and if you set as the last factory 
the factory which can handle the most elements (ex. list and header), then you will perform a lot of unnecessary operations under the hood. 

If any other, specific use case needs to be supported by your list, then you will need to extend one of the adapters and implement 
the code for your use case.
