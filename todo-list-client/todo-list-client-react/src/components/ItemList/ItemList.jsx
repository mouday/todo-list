import React, { Component } from 'react'
import PropTypes from 'prop-types'
import Item from '../Item/Item.jsx'

export default class ItemList extends Component {
  static propTypes = {
    list: PropTypes.array.isRequired,
    onRemoveItem: PropTypes.func.isRequired,
    onItemSelectedChange: PropTypes.func.isRequired,
  }

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
