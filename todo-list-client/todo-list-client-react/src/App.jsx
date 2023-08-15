import React, { Component } from 'react'
import './App.css'

import ItemList from './components/ItemList/ItemList.jsx'
import Header from './components/Header/Header.jsx'
import Footer from './components/Footer/Footer.jsx'
import { getUid } from './utils/uid-util.js'

export default class App extends Component {
  state = {
    list: [
      {
        id: '001',
        title: '吃饭',
        selected: false,
      },
      {
        id: '002',
        title: '睡觉',
        selected: false,
      },
    ],
  }

  handleAddItem = (title) => {
    const { list } = this.state

    const item = {
      id: getUid(),
      title,
      selected: false,
    }

    // 更新状态
    this.setState({
      list: [...list, item],
    })
  }

  handleRemoveItem = (id) => {
    const { list } = this.state

    this.setState({
      list: list.filter((item) => item.id !== id),
    })
  }

  handleItemSelectedChange = (data) => {
    const { list } = this.state

    const newList = list.map((item) => {
      return data.id == item.id ? { ...item, selected: data.selected } : item
    })

    this.setState({
      list: newList,
    })
  }

  handleRemoveSeleted = () => {
    const { list } = this.state

    this.setState({
      list: list.filter((item) => !item.selected),
    })
  }

  render() {
    const { list } = this.state

    return (
      <div className="app">
        <Header onAddItem={this.handleAddItem} />
        <ItemList
          list={list}
          onRemoveItem={this.handleRemoveItem}
          onItemSelectedChange={this.handleItemSelectedChange}
        />
        <Footer
          list={list}
          onRemoveSeleted={this.handleRemoveSeleted}
        />
      </div>
    )
  }
}
