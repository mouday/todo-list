import React, { Component } from 'react'
import Item from '../Item/Item.jsx'

export default class ItemList extends Component {
  render() {
    const { list, onRemoveItem, onItemSelectedChange } = this.props

    return (
      <div>
        {list.map((item) => {
          return (
            <Item
              key={item.id}
              {...item}
              onRemoveItem={onRemoveItem}
              onItemSelectedChange={onItemSelectedChange}
            />
          )
        })}
      </div>
    )
  }
}
