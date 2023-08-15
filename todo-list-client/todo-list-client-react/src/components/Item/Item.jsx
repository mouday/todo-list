import React, { Component } from 'react'
import './Item.css'

export default class Item extends Component {
  handleSelectedChange = (e) => {
    const { id, onItemSelectedChange } = this.props
    console.log(e.target.checked)
    onItemSelectedChange({ id, selected: e.target.checked })
  }

  render() {
    const { id, title, checked, onRemoveItem } = this.props

    return (
      <label className="item">
        <input
          type="checkbox"
          checked={checked}
          onChange={this.handleSelectedChange}
        />
        <div className="item__title">{title}</div>
        <div
          className="item__delete"
          onClick={() => onRemoveItem(id)}
        >
          移除
        </div>
      </label>
    )
  }
}
